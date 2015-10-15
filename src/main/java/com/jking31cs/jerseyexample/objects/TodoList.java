package com.jking31cs.jerseyexample.objects;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * This object holds the data for a TodoList.  A TodoList contains a list of items and an optional id.  The id is
 * optional so that we can allow the client to only pass us a list of items to serialize this object correctly.
 */
public class TodoList {
    private final Optional<Integer> id;
    private final List<Item> items;

    @JsonCreator
    public TodoList(
            @JsonProperty("id") Optional<Integer> id,
            @JsonProperty("items") List<Item> items
    ) {
        this.id = id;
        this.items = items;
    }


    public List<Item> getItems() {
        return items;
    }

    public Optional<Integer> getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoList todoList = (TodoList) o;
        return Objects.equals(id, todoList.id) &&
            Objects.equals(items, todoList.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, items);
    }
}
