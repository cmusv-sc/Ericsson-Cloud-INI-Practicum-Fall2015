#! /bin/bash

#This file takes in three paramters, target node IP, a port on target node and the name of service, it then deploys the service to that pot on the target node.

#has location of pem file
source pem_file.location
#has eureka ip and port
source eureka.location

echo $pemfile

#ssh into remote instance
ssh -T -o stricthostkeychecking=no -i "$pemfile" ubuntu@$1 << HERE

  sudo docker run -d --link mongo:mongo -e SERVICE_PORT=$2 -e HOST_IP=$1 -e MONGO_PORT=27017 -e EUREKA_IP=$EUREKA_IP -e EUREKA_PORT=$EUREKA_PORT -p $2:$2 abhishekshivanna/$3

HERE

echo "docker run -d --link mongo:mongo -e SERVICE_PORT=$2 -e HOST_IP=$1 -e MONGO_PORT=27017 -e EUREKA_IP=$EUREKA_IP -e EUREKA_PORT=$EUREKA_PORT -p $2:$2 abhishekshivanna/$3"
