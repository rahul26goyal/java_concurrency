package com.rahulg.datatypes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseFileReader { // public access modifier which makes this class consumable by other packages.

    protected Path filePath;

    protected BaseFileReader(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFilePath() {
        return filePath;
    }

    public List<String> readFile() throws IOException {
        return Files.lines(filePath)
            .map(this::mapFileLine).collect(Collectors.toList());
    }

    protected abstract String mapFileLine(String line);
}

class LowercaseFileReader extends BaseFileReader { // this class can not be public and only consumable within this package.

    public LowercaseFileReader(Path filePath) {
        super(filePath);
    }

    @Override
    public String mapFileLine(String line) {
        return line.toLowerCase();
    }
}

class TestClass {
    public  void test() throws URISyntaxException, IOException {

        URL fileURL = getClass().getClassLoader().getResource("testfile.txt");
        System.out.println("fileURL:::" + fileURL);
        Path filePath = Paths.get(fileURL.toURI());
        LowercaseFileReader reader = new LowercaseFileReader(filePath);
        List<String> fileContent = reader.readFile();
        System.out.println("File:::::" + fileContent.toString());
    }

    public  static void main(String ...args) throws URISyntaxException, IOException {
        TestClass t1 = new TestClass();
        t1.test();
    }
}