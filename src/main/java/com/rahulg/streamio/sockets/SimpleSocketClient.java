package com.rahulg.streamio.sockets;

import java.io.*;
import java.net.*;

public class SimpleSocketClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 1234;

        try (Socket socket = new Socket(hostname, port)) {
            // Create OutputStream to send data to the server
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            // Create InputStream to read data from the server
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Send a message to the server
            String message = "Hello from client!";
            writer.println(message);
            System.out.println("Sent to server: " + message);

            // Read the response from the server
            String response = reader.readLine();
            System.out.println("Received from server: " + response);

            // Close resources
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

