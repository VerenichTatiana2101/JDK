package org.example;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().printFile();
    }

    public void printFile() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("file.txt");
        System.out.println(new String(inputStream.readAllBytes()));

    }
}