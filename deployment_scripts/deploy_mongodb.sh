#! /bin/bash

#This file takes in three paramters, target node IP, a port on target node and the name of service, it then deploys the service to that pot on the target node.

source pem_file.location
#has eureka ip and port
source eureka.location

#ssh into remote instance
ssh -T -o stricthostkeychecking=no -i "$pemfile" ubuntu@$1 << HERE

  sudo docker run -p 27017:27017 --name mongo -d abhishekshivanna/mongo:v1

HERE
echo "sudo docker run -p 27017:27017 --name mongo -d abhishekshivanna/mongo:v1"
