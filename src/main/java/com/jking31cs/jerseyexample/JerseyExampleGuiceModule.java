package com.jking31cs.jerseyexample;

import com.google.inject.AbstractModule;
import com.jking31cs.jerseyexample.stores.TodoListStore;

import java.util.AbstractCollection;

/**
 * Created by jking31cs on 10/13/15.
 */
public class JerseyExampleGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TodoListStore.class);
    }
}
