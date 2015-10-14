package com.jking31cs.jerseyexample;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

/**
 * Created by jking31cs on 10/13/15.
 */
public class JerseyApplication extends ResourceConfig {

    @Inject
    public JerseyApplication(@Context ServletContext servletContext, ServiceLocator serviceLocator) {

        //registers the Jackson stuff
        register(JacksonJaxbJsonProvider.class);
        register(ObjectMapperResolver.class);

        // Then, enable the bridge between jersey and guice.
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(JerseyGuiceServletConfig.getOrCreateInjector());
    }
}
