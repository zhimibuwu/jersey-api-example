package com.jking31cs.jerseyexample.stores;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.base.Optional;
import com.jking31cs.jerseyexample.objects.TodoList;

/**
 * Nomrally, we'd have a DB Backend service here that handles the different transactions and select calls properly, but
 * I'm lazy and didn't want to implement that.
 */
@Singleton
public class TodoListStore {
    private final List<TodoList> lists;
    private Integer curId = 0;

    @Inject
    public TodoListStore() {
        this.lists = new ArrayList<>();
    }

    public TodoList get(Integer id) {
        for (TodoList list : lists) {
            if (list.getId().get().equals(id)) {
                return list;
            }
        }
        return null;
    }

    public TodoList save(TodoList list) {
        if (!list.getId().isPresent()) {
            TodoList newList = new TodoList(
                Optional.of(++curId),
                list.getItems()
            );
            lists.add(newList);
            return newList;
        }

        for (TodoList origList : lists) {
            if (origList.getId().equals(list.getId())) {
                origList.getItems().clear();
                origList.getItems().addAll(list.getItems());
                return origList;
            }
        }
        return null;

    }

    public List<TodoList> getAll() {
        return lists;
    }
}
