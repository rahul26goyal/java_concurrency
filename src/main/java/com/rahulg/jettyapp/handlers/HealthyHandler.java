package com.rahulg.jettyapp.handlers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;

public class HealthyHandler extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("HealthyHandler called..");
        System.out.flush();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("Pong");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
