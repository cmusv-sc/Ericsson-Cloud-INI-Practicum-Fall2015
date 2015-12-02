import sys
import os
import requests
import time

jmeter_dns = "ec2-52-90-201-208.compute-1.amazonaws.com"
gateway_dns = "ec2-107-23-54-201.compute-1.amazonaws.com"

def run_jmeter():
    jmeter_url = "http://" + jmeter_dns

    durations = {
        "constant" : 1 * 60,
        "step" : 1 * 60,
        "bell": 1 * 60
    }


    load = raw_input("Which load type do you want to benchmark? (constant, step, bell): ")

    if load != "constant" and  load != "step" and load != "bell":
        print "Unknown load type"
        sys.exit(1)


    print "Starting a Jmeter test for a " + load + " load over " + str(durations[load]) + " seconds"

# Start the Jmeter test
    resp = requests.get(jmeter_url + "/" + load + "?dns=" + gateway_dns)

    if resp.status_code != 200:
        print "JMeter did not return 200 OK"
        sys.exit(1)

    if "launched" not in resp.text:
        print "something weird happened"
        print resp.text
        sys.exit(1)

    log_url_idx0 = resp.text.index("/log?name=")
    log_url_idx1 = resp.text.index("'>Test")

    log_url = resp.text[log_url_idx0:log_url_idx1]

    log_abs_url = jmeter_url + log_url

    counter = 0

    while True:
        resp = requests.get(log_abs_url)
        if resp.status_code != 200:
            print "Did not get 200 for the Log URL"
            sys.exit(1)
        if "Goodbye!" in resp.text:
            break 
        time.sleep(10)
        counter += 10
        print "Test Progress: " + str(float(counter)/durations[load] * 100) + "%"

    print "JMeter Benchmark completed"
    result = resp.text
    print result

    start_idx = result.index("Average rps=")
    end_idx = result.index("</h4>\n\n")
    avg_rps = int(result[start_idx+12:end_idx])
    print "Average RPS: " + str(avg_rps)


# collect_sleuth collects all sleuth logs from individual services to the ./logs folder
def collect_sleuth():
    # TODO:expand for other nodes rather than just the gateway node
    print "Collecting Sleuth logs"
    base_dir =  "/home/ubuntu/Ericsson-Cloud-INI-Practicum-Fall2015/services/"
    log_subpaths = ["movie-service/movie_service.log",
                    "api-gateway/api_gateway.log",
                    "image-service/image_service.log",
                    "ratings-service/ratings_service.log",
                    "search-service/search_service.log",
                    "user-service/user_service.log"
                    ]

    logpaths = [base_dir + x for x in log_subpaths]
    os.system("mkdir logs")
    for path in logpaths:
        print path
        os.system("scp -oStrictHostKeyChecking=no -r -i ericsson.pem ubuntu@" + gateway_dns + ":" + path + " ./logs")


def parse_sleuth():
    # Parse_sleuth parses sleuth log messages to create the required messages of 
    # service_name, timestamp, service_class, workflow_id
    log_files = []
    for f in os.listdir(os.getcwd() + "/logs"):
        if f.endswith(".log"): 
            log_files.append((f, open(os.getcwd() + "/logs/" + f, 'r')))

    traces = {}
    trace_counter = 0

    result = []

    for filename, file in log_files:
        service_name = filename.split(".")[0]
        prev_stamp = 0
        for line in file:
            if "sleuth.log" not in line:
                continue
            # timestamps rounded to 10ms precision
            epoch_stamp = time.mktime(time.strptime(line[:19], "%Y-%m-%d %H:%M:%S")) + float("0" + line[19:23])

            name_idx = line.index("name=")
            trace_idx = line.index("traceId=")
            parents_idx = line.index("parents=[")
            endpoint = line[name_idx+5:trace_idx-2]
            trace_id = line[trace_idx+8:parents_idx-2]
            
            # Convert trace_id to workflow id
            if trace_id not in traces:
                traces[trace_id] = trace_counter
                trace_counter += 1

            workflow_id = traces[trace_id]

            result.append({
                "Service" : service_name,
                "Timestamp" : epoch_stamp,
                "Endpoint" : endpoint,
                "Workflow" : workflow_id
            })

    return result


def gen_table(intermediate):
    workflows = {}

    for entry in intermediate:
        if entry["Workflow"] not in workflows:
            workflows[entry["Workflow"]] = [{
                "Service" : entry["Service"],
                "Endpoint" : entry["Endpoint"],
                "Timestamp" : entry["Timestamp"],
                "Latency" : 0
            }]
        else:
            last_timestamp = workflows[entry["Workflow"]][-1]["Timestamp"]
            workflows[entry["Workflow"]].append({
                "Service" : entry["Service"],
                "Endpoint" : entry["Endpoint"],
                "Timestamp" : entry["Timestamp"],
                "Latency" : entry["Timestamp"] - last_timestamp
            })
    return workflows

def final_readable(final):
    for key, val in final.iteritems():
        print "-------------------"
        print "Workflow ID: " + str(key)
        for call in val:
            print "Service: " + call["Service"]
            print "Endpoint: " + call["Endpoint"]
            print "Latency: " + str(call["Latency"])
            print ""


if __name__ == "__main__":
    run_jmeter()
    collect_sleuth()
    intermediate = parse_sleuth()
    f = open("intermediate_results", "w+")
    f.write(str(intermediate))

    final = gen_table(intermediate)
    g = open("final_result", "w+")
    g.write(str(final))

    final_readable(final)

