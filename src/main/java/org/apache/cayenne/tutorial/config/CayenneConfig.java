package org.apache.cayenne.tutorial.config;

import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

@Provider
public class CayenneConfig extends ResourceConfig {

    public CayenneConfig() {
        packages("org.apache.cayenne.tutorial.service");
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindAsContract(CayenneServerRuntimeFactory.class);
            }
        });
    }
}
