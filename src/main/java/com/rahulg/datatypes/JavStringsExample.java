package com.rahulg.datatypes;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class JavStringsExample {


    // null or len=0
    static boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }

    // blank = "  "
    static boolean isBlankString(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static String generateRandom(int numBytes) {
        byte[] bytes = new byte[numBytes];
        new Random().nextBytes(bytes);

        String randomString = new String(bytes, StandardCharsets.US_ASCII); // the output may not be all ascii due to 8th bit issues..
        System.out.println("randomString::::".concat(randomString));
        StringBuilder randomBuilder = new StringBuilder();
        for (byte b: bytes){
            int int_value = (int) b;
            int_value = 97 + (int_value % 25);
            randomBuilder.append((char)int_value);
        }
        System.out.println("Char randomString::::".concat(randomBuilder.toString()));
        return randomString;

    }

    public static int countChars(String inputString, char c) {
        int value = (int) inputString.chars().filter(ch -> ch == c).count();
        return value;
    }


    public static void main(String[] args) {

        String s1 = "Hi Rahul";
        String s2 = "HI".concat(" Rahul");
        String s3 =  String.join(" ", "a", "b", "c");

        System.out.println(String.format("%s %s %s", s1,s2,s3));

        String s4 = new String("Hello!");
        s4 = s4.concat(" Rahul");
        System.out.println("S4===="+ s4);


        StringBuilder strBuilder  = new StringBuilder();
        int i = 0;
        while(i<10) {
            strBuilder.append("Hi\t");
            i++;
        }
        String finalString = strBuilder.toString();
        System.out.println("Final String built using builder: " + finalString);
        generateRandom(10);
        generateRandom(20);
        int count = countChars("cacatagca", ('c'));
        System.out.println("Count of c in :" + count);

    }

}

class StringEncodingTest {

    @Test
    public void encodingError() {
        String germanString = "Entwickeln Sie mit Vergnügen"; // this contains non-ascii char
        byte[] germanBytes = germanString.getBytes(); // we are not specifying any character set while encoding.
        System.out.println("germanBytes for::: "+ germanString);
        for(byte b: germanBytes){
            System.out.print(" " + b);
        }

        String asciiDecodedString = new String(germanBytes, StandardCharsets.US_ASCII);
        System.out.println("\n asciiDecodedString = " + asciiDecodedString); // this will print some unknown chars
        Assertions.assertNotEquals(germanString, asciiDecodedString);
    }

    /*
    Strings are immutable in Java, which means we cannot change a String character encoding.
    To achieve what we want, we need to copy the bytes of the String and then create a new one with the desired encoding.
     */

    @Test
    public void encodingUsingUTF8(){
        String rawString = "Entwickeln Sie mit Vergnügen";
        byte[] encodedBytes = rawString.getBytes(StandardCharsets.UTF_8); // the encoded bytes will follow the UTF-8 char set to generate bytes for each char
        System.out.println("encodedBytes = " + encodedBytes);

        String utf8decodedString = new String(encodedBytes, StandardCharsets.UTF_8); // the decoder will consideer the bytes as UTF-8 encoded bytes and generate UTF-8
                                                                                    // characters from the inpute sting which included german chars as well.
        System.out.println("utf8decodedString = " + utf8decodedString);
        Assertions.assertEquals(rawString, utf8decodedString);


    }

    @Test
    public void encodingUsingUTF8UsingApacheCommon(){
        String rawString = "Entwickeln Sie mit Vergnügen";
        byte[] utf8EncodedBytes = StringUtils.getBytesUtf8(rawString);
        // since utf-8 is a variable length encoding schema and we have non-ascii char, the size wll not be equal.
        System.out.println(String.format("Size of input: %d ; Number of bytes %d", rawString.length(), utf8EncodedBytes.length));

        String utf8DecodedString = StringUtils.newStringUtf8(utf8EncodedBytes);
        System.out.println("utf8DecodedString = " + utf8DecodedString);
        Assertions.assertEquals(rawString, utf8DecodedString);

        String asciidecodedString = StringUtils.newStringUsAscii(utf8EncodedBytes);
        Assertions.assertNotEquals(rawString, asciidecodedString);
    }

    @Test
    public void endocingDecodingInHexaDecimal() throws DecoderException {
        String input = "RahulGoyal"; // 10 chars
        byte[] inputBytes = StringUtils.getBytesUtf8(input);
        System.out.println("input :" + input);
        System.out.println("inputBytes :" + inputBytes.length);

        String  hexaString = Hex.encodeHexString(inputBytes);
        System.out.println("hexaString = " + hexaString);
        System.out.println("hexaString = " + hexaString.length());

        // do the revrse.. get the orignla string form hexa string.
        byte[] decodeBytes = Hex.decodeHex(hexaString);
        String decodedString = StringUtils.newStringUtf8(decodeBytes);
        System.out.println("decodedString :" + decodedString);
        System.out.println("decodeBytes :" + decodeBytes.length);
    }
}
