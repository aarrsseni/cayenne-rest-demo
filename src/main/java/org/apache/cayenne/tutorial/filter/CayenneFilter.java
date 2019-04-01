package org.apache.cayenne.tutorial.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.tutorial.config.CayenneServerRuntime;

public class CayenneFilter implements ContainerRequestFilter {

    private ObjectContext objectContext;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        objectContext = CayenneServerRuntime.getServerRuntime()
                .newContext();
    }

    public ObjectContext getObjectContext() {
        return objectContext;
    }
}
