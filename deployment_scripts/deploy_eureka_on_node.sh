#! /bin/bash

#This file takes in two paramters, target node IP, and a port on target node, it then deploys the eureka service to that pot on the target node. It also expors the IP and port to the file eureka.location


if [ "$#" -ne 2 ]; then
    echo "Enter two parameters - target node IP, and a port number. The eureka service will be deployed there"
    exit 1
fi

source pem_file.location

#ssh into remote instance
ssh -T -o stricthostkeychecking=no -i "$pemfile" ubuntu@$1 << HERE
  sudo docker run -d -e SERVICE_PORT=$2 -e HOST_IP=$1 -p $2:$2 abhishekshivanna/eureka
HERE

rm eureka.location
echo "EUREKA_IP=$1" >> eureka.location
echo "EUREKA_PORT=$2" >> eureka.location
