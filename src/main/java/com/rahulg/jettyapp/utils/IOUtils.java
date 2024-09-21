package com.rahulg.jettyapp.utils;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;

@UtilityClass
public class IOUtils {

    public static void readAndPrintFromBufferedReader(BufferedReader bufferedReader) throws IOException {
        String line;
        int count = 0;
        System.out.println("Reading from BufferedReader:::");
        while((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            count++;
        }
        System.out.println("iteration count = " + count);
    }
}
