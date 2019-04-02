package org.apache.cayenne.tutorial.config;

import org.apache.cayenne.configuration.server.ServerRuntime;

public class CayenneServerRuntimeFactory {

    private ServerRuntime serverRuntime;

    private ServerRuntime createRuntime() {
        serverRuntime = ServerRuntime.builder()
                .addConfig("cayenne-project.xml")
                .build();
        return serverRuntime;
    }

    public ServerRuntime get() {
        return serverRuntime == null ?
                createRuntime() :
                serverRuntime;
    }
}
