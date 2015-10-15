package com.jking31cs.jerseyexample;

import com.google.inject.AbstractModule;
import com.jking31cs.jerseyexample.stores.TodoListStore;

/**
 * This Guice Module handles binding our TodoListStore, meaning that when we inject the TodoListStore into another
 * service (like our WebService), it knows where and how to create/get that object.
 */
public class JerseyExampleGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TodoListStore.class);
    }
}
