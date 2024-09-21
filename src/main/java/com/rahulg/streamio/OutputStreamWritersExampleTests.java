package com.rahulg.streamio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

public class OutputStreamWritersExampleTests {
    private static final String OUTPUT_FILE_NAME = "sample_to_output_writer.txt";

    @BeforeEach
    public void setup() {
        File file = new File(OUTPUT_FILE_NAME);
        file.delete();
    }


    @Test
    public void test_write_to_file_using_OutputStreamWriter() throws IOException {
        // create a write from FileOutputStream
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(OUTPUT_FILE_NAME));
        final String msg = "Hello Rahul, \nHope you are doing well.";
        outputStreamWriter.write(msg);
        outputStreamWriter.flush();
        outputStreamWriter.close();

    }

    @Test
    public void test_write_to_file_using_BufferedWriter() throws IOException {
        final FileWriter fileWriter = new FileWriter(OUTPUT_FILE_NAME);
        final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        final String msg = "Hello Rahul, \nHope you are doing well.";
        bufferedWriter.write(msg);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    @Test
    public void test_write_to_ByteArrayOutputStream_using_BufferedWriter() throws IOException {
        // instead  of file as destination stream, we are creating a in-memory bytearray stream
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));
        final String hello = "Hello Rahul\n";
        final String greet = "Hope you are well....";

        try {
            bufferedWriter.write(hello);
            bufferedWriter.write(greet);
            bufferedWriter.flush();
            System.out.println("completed writing to buffer..");
            byte[] bytesWritten = byteArrayOutputStream.toByteArray();
            System.out.println("Arrays.toString(bytesWritten) = " + Arrays.toString(bytesWritten));
            String dataWritten  = new String(bytesWritten);
            System.out.println("dataWritten = \n" + dataWritten);
            // optinally, we can not write the bytesWritten to anyother output stream
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            bufferedWriter.close();
            byteArrayOutputStream.close();
        }
    }

    @Test
    public void test_write_to_file_using_PrintWriter() throws IOException {
        final String OUTPUT_FILE_NAME_PRINT = OUTPUT_FILE_NAME + "_print.txt";
        final PrintWriter printWriter = new PrintWriter(new FileWriter(OUTPUT_FILE_NAME_PRINT));
        final String hello = "Hello Rahul\n";
        final String greet = "Hope you are well....";
        try {
            printWriter.println(hello);
            printWriter.println(greet);
            printWriter.println(true);
            printWriter.println(12345);
            printWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            printWriter.close();
        }
    }
}
