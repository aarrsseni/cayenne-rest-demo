package org.apache.cayenne.tutorial.config;

import javax.ws.rs.ext.Provider;

import org.apache.cayenne.tutorial.filter.CayenneFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;

@Provider
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages("org.apache.cayenne.tutorial.service");
        register(new CayenneFilter());
        registerListener();
    }

    private void registerListener() {
        this.registerInstances(new ContainerLifecycleListener() {
            @Override
            public void onStartup(Container container) {
                CayenneServerRuntime.createRuntime();
            }

            @Override
            public void onReload(Container container) {
                CayenneServerRuntime.createRuntime();
            }

            @Override
            public void onShutdown(Container container) {
                CayenneServerRuntime.shutdownRuntime();
            }
        });
    }
}
