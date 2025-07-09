package com.rahulg.jettyapp;

import com.rahulg.jettyapp.handlers.HealthyHandler;
import com.rahulg.jettyapp.handlers.PingHandler;
import com.rahulg.jettyapp.handlers.UserHandler;
import com.rahulg.jettyapp.handlers.UserProfileServlet;
import jakarta.servlet.MultipartConfigElement;
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

        // Using getClass().getResource("")
        String classFilePath = MySampleJettyServer.class.getResource("").getPath();
        System.out.println("Class file path (getResource): " + classFilePath);
        contextHandler.setResourceBase(classFilePath);
        contextHandler.setAttribute("BasePath", classFilePath);
        // register handler for this context
        ServletHolder pingHolder = new ServletHolder("ping", PingHandler.class);
        contextHandler.addServlet(pingHolder, "/ping");

        ServletHolder healthHolder = new ServletHolder("health", HealthyHandler.class);
        contextHandler.addServlet(healthHolder, "/health");

        ServletHolder userHolder = new ServletHolder("user", UserHandler.class);
        contextHandler.addServlet(userHolder, "/user");

        ServletHolder userProfile = new ServletHolder("userProfile", UserProfileServlet.class);
        userProfile.getRegistration().setMultipartConfig(new MultipartConfigElement(
                null,             // Location where files should be stored (null means default temporary location)
                10 * 1024 * 1024, // Max file size (10 MB)
                50 * 1024 * 1024, // Max request size (50 MB)
                1024 * 1024       // File size threshold for storing files on disk (1 MB)
        ));
        contextHandler.addServlet(userProfile, "/userProfile");
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
