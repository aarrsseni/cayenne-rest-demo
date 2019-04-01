package org.apache.cayenne.tutorial.config;

import org.apache.cayenne.configuration.server.ServerRuntime;

public class CayenneServerRuntime {

    public static ServerRuntime serverRuntime;

    public static void createRuntime() {
        serverRuntime = ServerRuntime.builder()
                .addConfig("cayenne-project.xml")
                .build();
    }

    public static void shutdownRuntime() {
        if(serverRuntime != null) {
            serverRuntime.shutdown();
        }
    }

    public static ServerRuntime getServerRuntime() {
        return serverRuntime;
    }
}
