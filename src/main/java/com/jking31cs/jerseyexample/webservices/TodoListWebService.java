package com.jking31cs.jerseyexample.webservices;

import com.jking31cs.jerseyexample.objects.TodoList;
import com.jking31cs.jerseyexample.stores.TodoListStore;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by jking31cs on 10/13/15.
 */
@Path("api/todo-lists")
public class TodoListWebService {

    private final TodoListStore store;

    @Inject
    public TodoListWebService(TodoListStore store) {
        this.store = store;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TodoList> getAllLists() {
        return store.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TodoList getList(@PathParam("id") Integer id) {
        return store.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TodoList saveNewList(TodoList todoList) {
        return store.save(todoList);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TodoList updateList(@PathParam("id") Integer id, TodoList todoList) {
        return store.save(todoList);
    }


}
