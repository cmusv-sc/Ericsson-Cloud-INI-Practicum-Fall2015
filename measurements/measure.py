import sys
import os
import requests
from time import sleep

jmeter_dns = "ec2-54-209-36-71.compute-1.amazonaws.com"
gateway_dns = "ec2-54-85-216-124.compute-1.amazonaws.com"

jmeter_url = "http://" + jmeter_dns



durations = {
    "constant" : 5 * 60,
    "step" : 20 * 60,
    "bell": 20 * 60
}


'''
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
    sleep(10)
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

'''

# TODO:expand for other nodes as well

print os.getcwd()
#os.system("scp -r -i ericsson.pem ubuntu@" + gateway_dns + ":/home/ubuntu/logs ./logs")
log_files = []
for f in os.listdir(os.getcwd() + "/logs"):
    if f.endswith(".log"): 
        log_files.append((f, open(os.getcwd() + "/logs/" + f, 'r')))

for service, file in log_files:
    print service
    for line in file:
        print line
