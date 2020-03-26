/* Copyright © 2015 Oracle and/or its affiliates. All rights reserved. */
package com.example.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerService {

    private final CopyOnWriteArrayList<Customer> cList = MockCustomerList.getInstance();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer[] getAllCustomers() {
        return cList.toArray(new Customer[0]);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("id") long id) {
        Optional<Customer> match
                = cList.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new NotFoundException(new JsonError("Error", "Customer " + id + " not found"));
        }
    }
}