#! /bin/bash

if [ "$#" -ne 2 ]; then
    echo "Enter two parameters - target node IP, and a service name (should be the name of docker image)"
    exit 1
fi

#This file takes in two parameters. A target node IP and a service name. It generates a random port number and deploys the service on it

if [ $2 == "api-gateway" ]; then
   SERVICE_PORT=80
else 
   SERVICE_PORT=$(shuf -i 5000-50000 -n 1)
fi

./deploy_service_on_node_and_port.sh $1 $SERVICE_PORT $2
echo $SERVICE_PORT

