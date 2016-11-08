# WSO2 Carbon 5 Multitenancy

In Carbon 5, multitenancy is provided by the container cluster manager.
This repository contains a set of sample Carbon applications used for 
demonstrating how multitenancy works on Kubernetes for WSO2 middleware.

## How to run

1. Install a Kubernetes 1.4 cluster on your local machine by using 
[this](https://github.com/pires/kubernetes-vagrant-coreos-cluster.git) git repository.

2. Once the installation is completed execute the following command to 
verify the Kubernetes cluster status:

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
    
4. Create service docker images of each tenant:

    ```
    cd [wso2-c5-multitenancy]
    cd tenant-1/services/inventory-service/
    ./build.sh
    ./run.sh
    cd -
   
    cd tenant-2/services/human-resources-service/
    ./build.sh
    ./run.sh
    ```
   
5. Save docker images to the disk:
 
    ```
    cd [wso2-c5-multitenancy]
    cd tenant-1/services/inventory-service/
    ./save.sh
   
    cd tenant-2/services/human-resources-service/
    ./save.sh
    ```
   
6. Now copy above docker image files to the Kubernetes hosts and execute 
the below command in each host for loading them to the local docker registries:

    ```
    docker load < inventory-service.docker.image
    docker load < human-resources-service.docker.image
    ```
    
7. Now deploy above services in Kubernetes by executing below commands:
 
    ```
    cd [wso2-c5-multitenancy]
    kubectl create -f tenant-1/services/inventory-service/kubernetes/
    kubectl create -f tenant-2/services/human-resources-service/kubernetes/
    ```