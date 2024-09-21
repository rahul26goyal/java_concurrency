package com.rahulg.streamio.sockets;

import java.io.*;
import java.net.*;

public class ServerSocketExample {
    public static void main(String[] args) {
        int port = 1234; // Port on which the server will listen
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // Accept a client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            // Create InputStream to read data from the client
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Create OutputStream to send response to the client
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            // Read from the client
            String receivedMessage = reader.readLine();
            System.out.println("Received from client: " + receivedMessage);

            // Send a response to the client
            String responseMessage = "Hello from server!";
            writer.println(responseMessage);
            System.out.println("Sent to client: " + responseMessage);

            // Close resources
            reader.close();
            writer.close();
            clientSocket.close();
            serverSocket.close();
            System.out.println("Server closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

