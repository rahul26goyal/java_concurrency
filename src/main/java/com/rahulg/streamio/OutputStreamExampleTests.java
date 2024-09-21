package com.rahulg.streamio;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

public class OutputStreamExampleTests {
    private static final String OUTPUT_FILE_NAME = "sample_to_output.txt";

    @Test
    public void test_write_to_stdout_using_OutputStream() throws IOException {

        OutputStream sysStdOut = System.out;
        final String msg = "Hello Rahul, \nHope you are doing well.";

        try {
            final byte[] msgBytes = msg.getBytes();
            sysStdOut.write(msgBytes);
            sysStdOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_write_to_file_using_FileOutputStream() {
        final String msg = "Hello Rahul, \nHope you are doing well.";
        final File file = new File(OUTPUT_FILE_NAME);
        //file.delete();
        try(final OutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE_NAME, true)){
            fileOutputStream.write((msg).getBytes());
            fileOutputStream.flush();
            System.out.println("Completed writing to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_write_to_file_using_BufferedOutputStream() {
        final File file = new File(OUTPUT_FILE_NAME);
        final String msg = "Hello Rahul, \nHope you are doing well.";
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(OUTPUT_FILE_NAME))) {
            bufferedOutputStream.write((msg).getBytes());
            bufferedOutputStream.flush();
            System.out.println("Completed writing to file");
        } catch (Exception ex ){
            ex.printStackTrace();
        }
    }

    @Test
    public void test_write_string_to_ByteArrayOutputStream() throws IOException {
        
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final String hello = "Hello Rahul\n";
        final String greet = "Hope you are well....";
        
        try {
            // write first set of bytes
            byteArrayOutputStream.write(hello.getBytes());
            // write second set of bytes
            byteArrayOutputStream.write(greet.getBytes());
            byteArrayOutputStream.flush();
            System.out.println("Completed writing to ByteArrayOutputStream");
            
            byte[] bytesWritten = byteArrayOutputStream.toByteArray();
            System.out.println("Arrays.toString(bytesWritten) = " + Arrays.toString(bytesWritten));
            String dataWritten  = new String(bytesWritten);
            System.out.println("dataWritten = \n" + dataWritten);

            // Optionally, you can write this byte array to a file
            try (FileOutputStream fos = new FileOutputStream(OUTPUT_FILE_NAME)) {
                fos.write(bytesWritten);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            byteArrayOutputStream.close();
        }
    }
}
