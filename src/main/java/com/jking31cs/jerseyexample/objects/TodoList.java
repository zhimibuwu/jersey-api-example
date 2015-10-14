package com.jking31cs.jerseyexample.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by jking31cs on 10/13/15.
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
