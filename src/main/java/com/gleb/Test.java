package com.gleb;

import java.util.Scanner;

/**
 * Created by gleb on 05.06.15.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input search request");
        String key = scanner.nextLine();
        String os = System.getProperty("os.name").toLowerCase();
        String folderName = getFolderName(os);
        if (folderName == null) {
            throw new Exception("OS is not support");
        }
        System.setProperty("webdriver.gecko.driver", "driver/" + folderName + "/geckodriver");
        GoogleParser googleParser = new GoogleParser(key);
        googleParser.start();
    }

    private static String getFolderName(String os) {
        if (os.contains("mac")) {
            return "macOS";
        } else if (os.contains("windows")) {
            return "windows";
        } else if (os.contains("nix") || os.contains("nux")) {
            return "linux";
        }
        return null;
    }

}
