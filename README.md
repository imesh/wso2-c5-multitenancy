# WSO2 Carbon 5 Multitenancy

In Carbon 5, multitenancy is provided by the container cluster manager. This repository contains a set of sample Carbon applications used for demonstrating how multitenancy works on Kubernetes for WSO2 middleware.

## How to run

1. Setup a Kubernetes >=1.4 cluster with a single node on your local machine by using [this](https://github.com/pires/kubernetes-vagrant-coreos-cluster.git) git repository.

2. Once the installation is completed execute the following command to verify the Kubernetes cluster status:

    ```
    kubectl cluster-info
    
    Kubernetes master is running at http://172.17.8.101:8080
    Heapster is running at http://172.17.8.101:8080/api/v1/proxy/namespaces/kube-system/services/heapster
    kube-dns is running at http://172.17.8.101:8080/api/v1/proxy/namespaces/kube-system/services/kube-dns
    monitoring-grafana is running at http://172.17.8.101:8080/api/v1/proxy/namespaces/kube-system/services/monitoring-grafana
    ```

3. Execute following commands to create the namespaces:
    ```
    kubectl create namespace tenant-1
    kubectl create namespace tenant-2
    ```

4. Execute the below commands to create the Nginx ingress controller:
    
    ```
    cd [wso2-c5-multitenancy]
    kubectl create -f common/kubernetes/nginx-ingress-controller.yaml
    ```

    This will create an Nginx pod and expose port 80 from the Kubernetes node. In addition an ingress controller will get started for updating Nginx configuration according to the ingress definitions.

5. Create service docker images of each tenant:

    ```
    cd [wso2-c5-multitenancy]
    cd tenant-1/services/inventory-service/
    ./build.sh
    cd -
   
    cd tenant-2/services/human-resources-service/
    ./build.sh
    ```

5. Verify service docker images of each tenant:

    ```
    cd [wso2-c5-multitenancy]
    cd tenant-1/services/inventory-service/
    ./run.sh
    2016-11-08 10:01:59 INFO  MicroservicesRegistry:55 - Added microservice: org.wso2.msf4j.example.InventoryService@2ef1e4fa
    2016-11-08 10:01:59 INFO  MicroservicesRegistry:55 - Added microservice: org.wso2.msf4j.internal.swagger.SwaggerDefinitionService@5ccd43c2
    2016-11-08 10:01:59 INFO  NettyListener:68 - Starting Netty Http Transport Listener
    2016-11-08 10:02:00 INFO  NettyListener:110 - Netty Listener starting on port 8080
    2016-11-08 10:02:00 INFO  MicroservicesRunner:163 - Microservices server started in 462ms
    
    cd -
    cd tenant-2/services/human-resources-service/
    ./run.sh
    2016-11-08 10:01:35 INFO  MicroservicesRegistry:55 - Added microservice: org.wso2.msf4j.example.HumanResourcesService@2ef1e4fa
    2016-11-08 10:01:35 INFO  MicroservicesRegistry:55 - Added microservice: org.wso2.msf4j.internal.swagger.SwaggerDefinitionService@5ccd43c2
    2016-11-08 10:01:35 INFO  NettyListener:68 - Starting Netty Http Transport Listener
    2016-11-08 10:01:35 INFO  NettyListener:110 - Netty Listener starting on port 8080
    2016-11-08 10:01:35 INFO  MicroservicesRunner:163 - Microservices server started in 493ms
    ```
     
6. Save docker images to the disk:
 
    ```
    cd [wso2-c5-multitenancy]
    cd tenant-1/services/inventory-service/
    ./save.sh
   
    cd tenant-2/services/human-resources-service/
    ./save.sh
    ```
   
7. Copy above docker image files to the Kubernetes hosts and execute the below command in each host for loading them to the local docker registries:

    ```
    docker load < inventory-service.docker.image
    docker load < human-resources-service.docker.image
    ```
    
8. Now deploy above services in Kubernetes by executing below commands:
 
    ```
    cd [wso2-c5-multitenancy]
    kubectl create -f tenant-1/services/inventory-service/kubernetes/
    kubectl create -f tenant-2/services/human-resources-service/kubernetes/
    ```
    
9. Set an /etc/hosts entry for pointing 'kubernetes-nginx' host name to kubernetes node-01 ip address:

    ```
    vi /etc/hosts/
    
    172.17.8.102 kubernetes-nginx
    ```
    
10. Verify deployment using following HTTP requests:

    ```
    curl http://kubernetes-nginx/inventory
    {"version":"v1.0","resources":["/inventory"]}
    
    curl http://kubernetes-nginx/inventory/products
    [{"id":"Product 1","price":110.0,"qtyOnHand":10000},{"id":"Product 2","price":120.0,"qtyOnHand":20000},{"id":"Product 3","price":130.0,"qtyOnHand":30000},{"id":"Product 4","price":140.0,"qtyOnHand":40000},{"id":"Product 5","price":150.0,"qtyOnHand":50000},{"id":"Product 6","price":160.0,"qtyOnHand":60000},{"id":"Product 7","price":170.0,"qtyOnHand":70000},{"id":"Product 8","price":180.0,"qtyOnHand":80000},{"id":"Product 9","price":190.0,"qtyOnHand":90000},{"id":"Product 10","price":100.0,"qtyOnHand":100000},{"id":"Product 11","price":110.0,"qtyOnHand":120000}]
    
    curl http://kubernetes-nginx/human-resources/
    {"version":"v1.0","resources":["/employees"]}
        
    curl http://kubernetes-nginx/human-resources/employees
    [{"name":"Employee 1","age":21},{"name":"Employee 2","age":22},{"name":"Employee 3","age":23},{"name":"Employee 4","age":24},{"name":"Employee 5","age":25},{"name":"Employee 6","age":26},{"name":"Employee 7","age":27},{"name":"Employee 8","age":28},{"name":"Employee 9","age":29},{"name":"Employee 10","age":30}]
    ```