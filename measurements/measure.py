import sys
import os
import requests
import time

jmeter_dns = "ec2-54-209-36-71.compute-1.amazonaws.com"
gateway_dns = "ec2-107-23-54-201.compute-1.amazonaws.com"


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
    print "Test Progress: " + str(float(counter)/durations[load]) + "%"

print "JMeter Benchmark completed"
result = resp.text
print result

start_idx = result.index("Average rps=")
end_idx = result.index("</h4>\n\n")
avg_rps = int(result[start_idx+12:end_idx])
print "Average RPS: " + str(avg_rps)


# Get the latest timestamp from sleuth import logs
# Collect logs
# Parse logs

# TODO:expand for other nodes as well

base_dir =  "/home/ubuntu/Ericsson-Cloud-INI-Practicum-Fall2015/services/"
log_subpaths = ["movie-service/movie_service.log",
                "api-gateway/api_gateway.log",
                "image-service/image_service.log",
                "ratings-service/ratings_service.log",
                "search-service/search_service.log",
                "user-service/user_service.log"
                ]

logpaths = [base_dir + x for x in log_subpaths]

print "Collecting Sleuth logs"

os.system("mkdir logs")

for path in logpaths:
    print path
    os.system("scp -oStrictHostKeyChecking=no -r -i ericsson.pem ubuntu@" + gateway_dns + ":" + path + " ./logs")

log_files = []
for f in os.listdir(os.getcwd() + "/logs"):
    if f.endswith(".log"): 
        log_files.append((f, open(os.getcwd() + "/logs/" + f, 'r')))

for filename, file in log_files:
    service_name = filename.split(".")[0]
    prev_stamp = 0
    for line in file:
        try:
            # timestamps rounded to 10ms precision
            epoch_stamp = time.mktime(time.strptime(line[:19], "%Y-%m-%d %H:%M:%S"))
            epoch_stamp += float("0" + line[19:23])

            name_idx = line.index("name=")
            trace_idx = line.index("traceId=")
            service_class = line[name_idx+5:trace_idx-2]

            if prev_stamp == 0:
                latency = 0
            else:
                latency = epoch_stamp - prev_stamp
            prev_stamp = epoch_stamp

            print "Service Name: " + service_name
            print "Timestamp: " + str(epoch_stamp)
            print "Service Class: " + service_class
            #print "latency: " + str(latency)
            print " "
        except:
            pass

#TODO: ignore previous log timestamps
