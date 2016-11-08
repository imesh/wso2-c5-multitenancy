package org.wso2.msf4j.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory API information definition.
 */
public class InventoryServiceInfo {

    private static InventoryServiceInfo instance;

    private final String version;
    private final List<String> resources;

    private InventoryServiceInfo() {
        version = "v1.0";
        resources = new ArrayList<>();
        resources.add("/inventory");
    }

    public static InventoryServiceInfo getInstance() {
        if(instance == null) {
            instance = new InventoryServiceInfo();
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
