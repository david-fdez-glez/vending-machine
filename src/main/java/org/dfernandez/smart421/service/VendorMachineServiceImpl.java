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

        List<Coin> changeReturn = new ArrayList<>();

        if(getChange(pence,true, changeReturn, false) ) {
            getChange(pence,false, changeReturn, false);
        }

        return changeReturn;
    }

    /**
     * Refresh Coin Inventory
     * @return
     */
    public Map<Coin, Integer> getCoinsMap() {
        refreshCoinRepo(pathToCoinRepo);
        return  coinsMap;
    }

    private boolean getChange(int pence, boolean isMock, List<Coin> outList, boolean isUnlimited) {
        int amountLeft = pence;

        // Flag for Unlimited coins
        boolean flagAmount = true;

        // Assume an unlimited supply of coins
        for(Coin coin: Coin.values()) {
            // If we need to check the  number of coins
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
                }
            }
        }

        return amountLeft ==0;
    }


    private void refreshCoinRepo(String path) {
        coinsMap = FilesUtil.readCoinsValuesFromFile(path);
    }



    private int getCoinAmount(Coin coin) {
        return coinsMap.get(coin);
    }

    private void updateCoins(Coin coin, int amount) {
        // Update Map
        coinsMap.put(coin, amount + getCoinAmount(coin));
        // Update File
        writeCoinsValuesToFile(pathToCoinRepo, coinsMap);
    }

    private void writeCoinsValuesToFile(String path, Map<Coin, Integer> map) {
        FilesUtil.writeCoinsValuesToFile(path,map);
    }
    /*
    @Override
    public void refreshCoinRepo(String path) {
        coinsMap = FilesUtil.readCoinsValuesFromFile(path);
    }

    @Override
    public Map<Coin, Integer> readCoinsValuesFromFile(String path) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void writeCoinsValuesToFile(String path, Map<Coin, Integer> map) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    */
}
