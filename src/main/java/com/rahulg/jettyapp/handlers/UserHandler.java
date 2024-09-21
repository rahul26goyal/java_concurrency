package com.rahulg.jettyapp.handlers;


import com.google.gson.Gson;
import com.rahulg.jettyapp.models.request.CreateUserRequest;
import com.rahulg.jettyapp.models.response.CreateUserResponse;
import com.rahulg.jettyapp.utils.IOUtils;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UserHandler extends HttpServlet {

    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.gson = new Gson(); // Initialize Gson when the servlet is initialized
        // this gson object will not honour the lombok annotations which we have defined.
        System.out.println("UserHandler initialized..");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Create User called...");
        final InputStream requestInputStream = request.getInputStream();
        final BufferedReader requestReader = new BufferedReader(new InputStreamReader(requestInputStream));
        //IOUtils.readAndPrintFromBufferedReader(requestReader);
        final CreateUserRequest createUserRequest = gson.fromJson(requestReader, CreateUserRequest.class);
        System.out.println("createUserRequest = " + createUserRequest);
        // perform validations here..
        final CreateUserResponse createUserResponse = CreateUserResponse.builder()
                .id("2121")
                .username("user-1")
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        // writes the json string back to the response stream.
        response.getWriter().write(gson.toJson(createUserResponse));
    }
}
