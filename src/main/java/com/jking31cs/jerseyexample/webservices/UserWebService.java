package com.jking31cs.jerseyexample.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jking31cs.jerseyexample.objects.User;
import com.jking31cs.jerseyexample.stores.UserStore;

/**
 * This web service handles all the different http calls from a client to create, read, update, and delete TodoLists.
 * This is done using Jersey/JAX-RS (Java Application Rest Service API).
 */
@Path("api/users")
public class UserWebService {

    private final UserStore store;

    @Inject
    public UserWebService(UserStore store) {
        this.store = store;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllLists() {
        return store.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getList(@PathParam("id") Long id) {
        return store.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User saveNewList(User user) {
        return store.save(user);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateList(@PathParam("id") Long id, User user) {
        return store.save(id, user);
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User deleteList(@PathParam("id") Long id) {
        return store.delete(id);
    }


}
