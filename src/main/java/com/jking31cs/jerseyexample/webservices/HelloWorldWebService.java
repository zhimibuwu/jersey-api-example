package com.jking31cs.jerseyexample.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by jking31cs on 10/13/15.
 */
@Path("api/hello-world")
@Produces("text/plain")
public class HelloWorldWebService {

    @GET
    public String hello() {
        return "Hello World.";
    }
}
