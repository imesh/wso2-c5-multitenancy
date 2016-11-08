/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.msf4j.example;

import org.wso2.msf4j.example.domain.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Inventory service class.
 */
@Path("/inventory")
public class InventoryService {

    @GET
    @Path("/")
    @Produces("application/json")
    public ServiceInfo getApiMetadata() {
        System.out.println("---> API Resource GET / invoked");
        return ServiceInfo.getInstance();
    }

    @GET
    @Path("/products")
    @Produces("application/json")
    public List<Product> getProducts() {
        System.out.println("---> API Resource GET /products invoked");
        return Database.getInstance().getProducts();
    }
}
