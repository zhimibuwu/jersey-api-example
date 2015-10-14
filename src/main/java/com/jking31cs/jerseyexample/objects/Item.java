package com.jking31cs.jerseyexample.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by jking31cs on 10/13/15.
 */
public class Item {
    private final boolean done;
    private final String message;

    @JsonCreator
    public Item(
            @JsonProperty("done") boolean done,
            @JsonProperty("message") String message) {
        this.done = done;
        this.message = message;
    }

    public boolean isDone() {
        return done;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(done, item.done) &&
            Objects.equals(message, item.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(done, message);
    }
}
