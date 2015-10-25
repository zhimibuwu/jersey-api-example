package com.jking31cs.jerseyexample.stores;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.jking31cs.jerseyexample.objects.User;

import javax.inject.Inject;
import java.util.List;

/**
 * This is where we do all the saving to/from the datastore using Objectify.
 */
public class UserStore {

    private final Objectify objectify;

    @Inject
    public UserStore(Objectify objectify) {
        this.objectify = objectify;
    }

    public List<User> getAll() {
        return objectify.load().type(User.class).list();
    }

    public User get(Long id) {
        return objectify.load().type(User.class).id(id).now();
    }

    public User save(User user) {

        Key<User> key = objectify.save().entity(user).now();
        return objectify.load().key(key).now();
    }

    public User save(Long id, User user) {
        User newuser = objectify.load().type(User.class).id(id).now();
        newuser.setName(user.getName());
        newuser.setEmail(user.getEmail());
        return newuser;
    }

    public User delete(Long id) {
        User newuser = objectify.load().type(User.class).id(id).now();
        objectify.delete().entity(newuser).now();
        return newuser;
    }
}