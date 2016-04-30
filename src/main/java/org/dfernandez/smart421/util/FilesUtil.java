package org.dfernandez.smart421.util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FilesUtil {

   // private static final String COIN_INVENTORY_PATH = "src/test/resources/coin-inventory-read.properties";

    public static List<String> readAllLines(String path) {
        List<String> lines = new ArrayList<>();
        try {
           lines = Files.readAllLines(Paths.get(path));

        } catch (IOException exception) {
          // TODO ERROR
        }
         return lines;
    }


    public static void writeToTextFile(String path, String content) {
        try {
            Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        }  catch (IOException exception) {
            // TODO ERROR
        }
    }
}
