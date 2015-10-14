package com.jking31cs.jerseyexample;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

/**
 * Created by jking31cs on 10/13/15.
 */
public class JerseyGuiceServletConfig extends GuiceServletContextListener {

    private static Injector injector;

    @Override
    protected Injector getInjector() {
        return injector;
    }

    public static Injector getOrCreateInjector() {

        if (injector == null) {
            injector = Guice.createInjector(
                new ServletModule(),
                new JerseyExampleGuiceModule()
            );
        }
        return injector;
    }
}
