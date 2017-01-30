package com.gleb;

import java.util.Scanner;

/**
 * Created by gleb on 05.06.15.
 */
public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input search request");
        String key = scanner.nextLine();
        System.setProperty("webdriver.gecko.driver", "../geckodriver");
        GoogleParser googleParser = new GoogleParser(key);
        googleParser.start();
    }

}
