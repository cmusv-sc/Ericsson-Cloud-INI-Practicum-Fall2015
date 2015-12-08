#! /bin/bash

# Deploy mongo image everywhere
while IFS='' read -r line || [[ -n "$line" ]]; do
    echo "Deploying mongodb on the node: $line"
    ./deploy_mongodb.sh $line
done < node.list



EUREKA_NODE=$(shuf -n 1 node.list)
echo "$EUREKA_NODE"

SERVICE_PORT=$(shuf -i 20000-60000 -n 1)

./deploy_eureka_on_node.sh $EUREKA_NODE $SERVICE_PORT
echo $SERVICE_PORT



