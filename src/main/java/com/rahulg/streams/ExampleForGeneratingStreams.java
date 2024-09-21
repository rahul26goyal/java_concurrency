package com.rahulg.streams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExampleForGeneratingStreams {

    @Test
    public void test_generate_streams() {
        Stream<Integer> intStream = Stream.empty();
        Assertions.assertTrue(intStream.findFirst().isEmpty());

        System.out.println("intStream = " + intStream);
        Stream<String> stringStream = Stream.of("A", "B", "C");
        System.out.println("stringStream = " + stringStream.toString());
        Assertions.assertTrue(stringStream.findFirst().isPresent());
        // stream can only be operated once.
        Assertions.assertThrows(IllegalStateException.class, () -> stringStream.count());

        Integer[] integers = {1,2,3,4,5};
        Stream<Integer> integerStream = Stream.of(integers);
        System.out.println("integerStream = " + integerStream.collect(Collectors.toList()));
    }

    @Test
    public void test_stream_builders() {
        Stream.Builder<String> stringBuilder = Stream.builder();
        stringBuilder.accept("Rahul"); // retuens void
        stringBuilder.add("Goyal");  // returns updated steam
        Assertions.assertEquals(2, stringBuilder.build().count());
    }

    @Test
    public void test_build_stream_from_collection() {
        List<Integer> integerList = List.of(1,2,3,4,5,6,7);
        Stream<Integer> integerStream = integerList.stream();
        // filter and forEach example
        integerStream.
            filter((num) -> num %2 == 0)
            .forEach(System.out::print);
        System.out.println("\n integerStream = " + integerStream);
        String[] strings = new String[]{"sa", "re","ga", "ma"};
        
        Stream<String> stringStream = Arrays.stream(strings);
        // map and collect example
        List<String> list = stringStream.
            map((str) -> String.format("%s:%s",str, str))
            .collect(Collectors.toList());
        System.out.println("list = " + list);
    }

    @Test
    public void test_stream_with_flatmaps() {

        ArrayList<Integer> nums = new ArrayList<>(List.of(1,2,3,4,5,6, -3));

        Stream<Integer> integerStream = nums.stream();

        List<Integer> repeatedCountList = integerStream
            .filter((i) -> i > 0) // chose only positive numbers
            .flatMap((i) -> {
                ArrayList<Integer> result = new ArrayList<>(i);
                IntStream.range(0,i)
                    .forEach((num) -> result.add(i));
                // returns a new stream of numbers
                return result.stream();
            })
            .collect(Collectors.toList()); // combine all streams into one.
        System.out.println("repeatedCountList = " + repeatedCountList);
    }

    @Test
    public void test_stream_matching_operations() {

        final ArrayList<Integer> nums = new ArrayList<>(List.of(0,2,4,6,8));
        System.out.println("nums = " + nums);
        // check if all even numbers
        Boolean allEvenNums = nums.stream()
            .peek((num) -> System.out.println("Checking Event for = " + num)) // retuens the stream
            .allMatch((num) -> num % 2 == 0); // terminal operations
        System.out.println("allEvenNums = " + allEvenNums);

        nums.add(3);
        System.out.println("nums = " + nums);
        allEvenNums = nums.stream()
            .allMatch((num) -> num % 2 == 0); // terminal operations
        System.out.println("allEvenNums = " + allEvenNums);
        
        Boolean anyOdd = nums.stream()
            .anyMatch((num) -> num % 2 == 1);
        System.out.println("anyOdd = " + anyOdd);

        // noneMatch is there but can be ignored.
        long currentDateMS = System.currentTimeMillis();
        System.out.println("currentDateMS = " + currentDateMS);

    }

    private static String getUtcDateString(Date date, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat, Locale.ROOT); // $NON-NLS-L$ hardcoded locale
        df.setTimeZone(TimeZone.getTimeZone("UTC")); // $NON-NLS-L$ hardcoded time zone ID
        return df.format(date);
    }

    @Test
    public  void test_date() {
        Date now = new Date(1697134137000l);
        Instant inow = now.toInstant();
        System.out.println("inow = " + inow);
        System.out.println("now = " + now);
        String DATE_FORMAT_FOR_DISPLAY = "yyyy.MM.dd HH:mm:ss z";
        String utc = getUtcDateString(now, DATE_FORMAT_FOR_DISPLAY);
        System.out.println("utc = " + utc);

    }



}
