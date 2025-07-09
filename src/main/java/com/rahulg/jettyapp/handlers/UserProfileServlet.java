package com.rahulg.jettyapp.handlers;

import com.rahulg.jettyapp.utils.S3Uploader;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.eclipse.jetty.http.HttpTester;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1 MB
        maxFileSize = 1024 * 1024 * 5,   // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class UserProfileServlet extends HttpServlet {

    private S3Uploader s3Uploader;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.s3Uploader = new S3Uploader();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Received upload request..");
        Part picturePart = req.getPart("picture");
        String fileName = picturePart.getSubmittedFileName();
        System.out.println("fileName = " + fileName);
        String fileSuffix = new File(fileName).getName();
        System.out.println("fileSuffix = " + fileSuffix);
        InputStream inputStream = picturePart.getInputStream();
        try {
            String preSignedUrl = s3Uploader.uploadFile(fileName, inputStream);
            System.out.println("preSignedUrl = " + preSignedUrl);
            resp.getWriter().write("File uploaded successfully to S3. Presigned URL: " + preSignedUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the content type to HTML
        response.setContentType("text/html");
        System.out.println("Request Context path: " + request.getContextPath());
        System.out.println("Context path: " + getServletContext().getContextPath());
        // Load the HTML file from the "static" folder
        String htmlContent = loadHtmlFromFile("/Users/rhgoyal/learning/github-repos/java_concurrency/src/main/java/com/rahulg/jettyapp/static/userProfile.html");

        // Write the HTML content to the response
        response.getWriter().write(htmlContent);
    }

    // Utility method to load HTML file from the "static" folder
    private String loadHtmlFromFile(String filePath) throws IOException {
        //InputStream inputStream = getServletContext().getResourceAsStream(filePath);
        //filePath = getServletContext().getAttribute("BasePath") + filePath;
        System.out.println("filePath = " + filePath);
        InputStream inputStream = new FileInputStream(filePath);
        if (inputStream == null) {
            throw new IOException("File not found: " + filePath);
        }

        // Use Scanner to read the HTML file as a string
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name()).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

}
