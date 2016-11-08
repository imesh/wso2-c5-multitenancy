package org.wso2.msf4j.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory API information definition.
 */
public class ServiceInfo {

    private static ServiceInfo instance;

    private final String version;
    private final List<String> resources;

    private ServiceInfo() {
        version = "v1.0";
        resources = new ArrayList<>();
        resources.add("/products");
    }

    public static ServiceInfo getInstance() {
        if(instance == null) {
            instance = new ServiceInfo();
        }
        return instance;
    }

    public String getVersion() {
        return version;
    }

    public List<String> getResources() {
        return resources;
    }
}
