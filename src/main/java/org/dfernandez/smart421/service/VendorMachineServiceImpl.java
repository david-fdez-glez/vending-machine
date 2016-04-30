package org.dfernandez.smart421.service;


import org.dfernandez.smart421.model.Coin;
import org.dfernandez.smart421.util.FilesUtil;

import java.util.*;

public class VendorMachineServiceImpl implements VendorMachineService {

    // Map with all coin inventory
    private Map<Coin, Integer> coinsMap;
    // Path to file where is the coin inventory
    private static String pathToCoinRepo;

    public VendorMachineServiceImpl() {
    }
     // Constructor for Part 2
    public VendorMachineServiceImpl(String path) {
        pathToCoinRepo = path;
        refreshCoinRepo(pathToCoinRepo);
    }

    /**
     * Return the least number of coins possible
     * Assume an unlimited supply of coins.
     * @param pence integer
     * @return Collection of coins(optimal change)
     */
    @Override
    public Collection<Coin> getOptimalChangeFor(int pence) {

        List<Coin> changeReturn = new ArrayList<>();

        getChange(pence,false, changeReturn, true);

        return changeReturn;
    }

    /**
     * get the change for a given number of pence based on a limited supply of coins
     * @param pence
     * @return
     */
    @Override
    public Collection<Coin> getChangeFor(int pence) {

        // Update Coins Values
        refreshCoinRepo(pathToCoinRepo);
        List<Coin> changeReturn = new ArrayList<>();

        if(getChange(pence,true, changeReturn, false) ) {
            getChange(pence,false, changeReturn, false);
            return changeReturn;

        }
        throw new IllegalArgumentException("Insufficient coins!!");
    }

    /**
     * Refresh Coin Inventory
     * @return
     */
    public Map<Coin, Integer> getCoinsMap() {
        refreshCoinRepo(pathToCoinRepo);
        return  coinsMap;
    }

    /**
     *
     * @param pence
     * @param isMock
     * @param outList
     * @param isUnlimited
     * @return  true if  the system is able to return the exact change, and if is able to, it will change outList
     */
    private boolean getChange(int pence, boolean isMock, List<Coin> outList, boolean isUnlimited) {
        int amountLeft = pence;

        // Flag for Unlimited coins
        boolean flagAmount = true;


        for(Coin coin: Coin.values()) {
            // If  we don't assume an unlimited supply of coins
            if(!isUnlimited) {
                flagAmount = getCoinAmount(coin) >0;
            }
            while(amountLeft >= coin.getDenomination() && flagAmount) {
                amountLeft -= coin.getDenomination();

                // Add Coin to result
                if(isMock == false) {
                    outList.add(coin);
                }
                // Update Coin Repository Values
                if(isMock == false && !isUnlimited) {
                    updateCoins(coin, -1);
                    flagAmount = getCoinAmount(coin) > 0;
                }
            }
        }

        return amountLeft == 0;
    }


    // Update Map with Files Value
    private void refreshCoinRepo(String path) {
        coinsMap = FilesUtil.readCoinsValuesFromFile(path);
    }


    // Return Coin amount
    private int getCoinAmount(Coin coin) {
        return coinsMap.get(coin);
    }

    // Update Coins Values
    private void updateCoins(Coin coin, int amount) {
        // Update Map
        coinsMap.put(coin, amount + getCoinAmount(coin));
        // Update File
        writeCoinsValuesToFile(pathToCoinRepo, coinsMap);
    }

    // Update File Value
    private void writeCoinsValuesToFile(String path, Map<Coin, Integer> map) {
        FilesUtil.writeCoinsValuesToFile(path,map);
    }

}
