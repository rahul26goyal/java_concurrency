package com.rahulg.jettyapp;

import com.rahulg.jettyapp.handlers.HealthyHandler;
import com.rahulg.jettyapp.handlers.PingHandler;
import com.rahulg.jettyapp.handlers.UserHandler;
import jakarta.servlet.Servlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MySampleJettyServer {
    private static final Integer PORT = 8080;

    public static void main(String[] args) throws Exception {
        Server jettyServer = new Server(PORT);
        // create a ServletContextHandler for handling the roor.
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        // register handler for this context
        ServletHolder pingHolder = new ServletHolder("ping", PingHandler.class);
        contextHandler.addServlet(pingHolder, "/ping");

        ServletHolder healthHolder = new ServletHolder("health", HealthyHandler.class);
        contextHandler.addServlet(healthHolder, "/health");

        ServletHolder userHolder = new ServletHolder("user", UserHandler.class);
        contextHandler.addServlet(userHolder, "/user");
        // set handler for server
        jettyServer.setHandler(contextHandler);
        try {
            jettyServer.start();
            System.out.println("Server started at http://localhost:8080");
            jettyServer.join();

        } catch (Exception e) {
            System.out.println("Exception occurred while starting the server.. = " + e);
            e.printStackTrace();
        }

    }
}
