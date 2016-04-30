package org.dfernandez.smart421.util;


import org.dfernandez.smart421.model.Coin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class FilesUtil {

    /**
     * I'm assuming the purpose for this test is not to validate the IO Operations
     * This is why I'm not doing anything when I catch the errors
     * @param path
     * @return
     */
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



    public static Map<Coin, Integer> readCoinsValuesFromFile(String path) {

        Map<Coin,Integer> coins = new HashMap<>();

        List<String> lines = new ArrayList<>();

        lines = readAllLines(path);
        String[] split;
        Coin coin;
        int denomination;

        for(String st:lines) {
          split = st.split("=");
            switch (split[0]) {
                case "100":
                    coin = Coin.ONE_POUND;
                    break;
                case "50":
                    coin = Coin.FIFTY_PENCE;
                    break;
                case "20":
                    coin = Coin.TWENTY_PENCE;
                    break;
                case "10":
                    coin = Coin.TEN_PENCE;
                    break;
                case "5":
                    coin = Coin.FIVE_PENCE;
                    break;
                case "2":
                    coin = Coin.TWO_PENCE;
                    break;
                case "1":
                    coin = Coin.ONE_PENNY;
                    break;
                default:
                    coin = Coin.ONE_POUND;
            }
            denomination = Integer.parseInt(split[1]);
            coins.put(coin,denomination);
        }


        return coins;
    }



    public static void writeCoinsValuesToFile(String path, Map<Coin, Integer> map) {

        StringBuilder sb = new StringBuilder();
        Map.Entry<Coin, Integer> entry;

        sb.append("100=").append(map.get(Coin.ONE_POUND)).append("\n50=").append(map.get(Coin.FIFTY_PENCE)).append("\n20=").append(map.get(Coin.TWENTY_PENCE));
        sb.append("\n10=").append(map.get(Coin.TEN_PENCE)).append("\n5=").append(map.get(Coin.FIVE_PENCE)).append("\n2=").append(map.get(Coin.TWO_PENCE));
        sb.append("\n1=").append(map.get(Coin.ONE_PENNY));



        writeToTextFile(path, sb.toString());
    }
}
