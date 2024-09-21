package com.rahulg.streamio;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class InputStreamAndReaderTests {
    private static final String INPUT_FILE_NAME = "sample_input.txt";


    @Test
    public void test_file_read_using_Files_readString_method() {
    }

    /**
     * The FileInputStream class is used to read raw bytes from a file,
     * which is ideal for reading binary data like images, audio, or video files.
     *
     * once opened, you can read the stream only once from beginning to end.
     */
    @Test
    public void test_read_file_using_FileInputStream_without_buffering() throws Exception {
        final InputStream fileInputStream = new FileInputStream(INPUT_FILE_NAME);
        System.out.println("fileInputStream.available() = " + fileInputStream.available());
        int read = fileInputStream.read(); // reads one byte at a time.
        while(read != -1) {
            //System.out.println("read = " + read);
             System.out.print((char) read);
             read = fileInputStream.read();
        }
        fileInputStream.close(); // close the stream
    }

    /**
     * A BufferedInputStream adds functionality to another input stream-namely, the ability to buffer the input and to support the mark and reset methods.
     * When the BufferedInputStream is created, an internal buffer array is created. As bytes from the stream are read or skipped, the internal buffer is refilled as necessary from the contained input stream, many bytes at a time. The mark operation remembers a point in the input stream and the reset operation causes all the bytes read since the most recent mark operation to be reread before new bytes are taken from the contained input stream.
     * @throws Exception
     */
    @Test
    public void test_read_file_using_FileInputStream_with_buffering() throws Exception {
        System.out.println("Reading the input file using BufferedInputStream");
        final InputStream fileInputStream = new FileInputStream(INPUT_FILE_NAME);
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        bufferedInputStream.mark(0); // to enable reading multiple times.
        readAndPrintInputSteam(bufferedInputStream);
        System.out.println("readAndPrintInputSteam completed");
        bufferedInputStream.reset(); // reset and read again from buffered stream
        readAndPrintInputSteam(bufferedInputStream);
        bufferedInputStream.close();
    }

    /**
     * Reading a bytearray as input steam.
     * convert the ayte array into a stream and read it. => ByteArrayInputStream
     * @throws IOException
     */
    @Test
    public void test_reading_byte_array_as_InputStream() throws IOException {
        final byte[] byteArrayFromString = "Hello Rahul, Hope you are well".getBytes(StandardCharsets.UTF_8);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayFromString);
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream);
        System.out.println("Reading bytes from input stream:::");
        //readAndPrintInputSteam(byteArrayInputStream);
        System.out.println("Reading bytes from BufferedInputStream:::");
        readAndPrintInputSteam(bufferedInputStream);
        byteArrayInputStream.close();
        bufferedInputStream.close();
    }

    private void readAndPrintInputSteam(final InputStream inputStream) throws IOException {
        int read = inputStream.read();
        while(read != -1) {
            System.out.print((char) read);
            read = inputStream.read();
        }
    }

    @Test
    public void test_read_file_using_FileReader() throws IOException {
        final FileReader fileReader = new FileReader(INPUT_FILE_NAME);
        readAndPrintFromInputSteamReader(fileReader);
        fileReader.close();
    }

    @Test
    public void read_read_file_using_BufferedReader() throws IOException {
        final FileReader fileReader = new FileReader(INPUT_FILE_NAME);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        readAndPrintFromInputSteamReader(bufferedReader);
        fileReader.close();
        bufferedReader.close();
    }

    @Test
    public void read_read_file_using_BufferedReader_readLine() throws IOException {
        final FileReader fileReader = new FileReader(INPUT_FILE_NAME);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        readAndPrintFromBufferedReader(bufferedReader);
        fileReader.close();
        bufferedReader.close();
    }

    private void readAndPrintFromInputSteamReader(Reader inputStreamReader) throws IOException {
        System.out.println("reading from Reader:::");
        int read = inputStreamReader.read();
        int count = 0;
        while(read != -1) {
            System.out.print((char) read); //
            read = inputStreamReader.read();
            count++;
        }
        System.out.println("\niteration count = " + count);
    }

    private void readAndPrintFromBufferedReader(BufferedReader bufferedReader) throws IOException {
        String line;
        int count = 0;
        System.out.println("Reading from BufferedReader:::");
        while((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            count++;
        }
        System.out.println("iteration count = " + count);
    }

    /**
     * Shows an example of how we can convert a string into BufferedReader
     *
     * @throws IOException
     */
    @Test
    public void test_reading_byte_array_as_BufferedReader() throws IOException {
        final byte[] byteArrayFromString = "Hello Rahul, Hope you are well".getBytes(StandardCharsets.UTF_8);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayFromString);
        //final BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream);
        final BufferedReader byteArrayReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArrayFromString)));
        //readAndPrintInputSteam(byteArrayInputStream);
        System.out.println("Reading bytes from BufferedReader:::");
        readAndPrintFromInputSteamReader(byteArrayReader);
        byteArrayInputStream.close();
        byteArrayReader.close();
    }
}
