package org.dfernandez.smart421.service;


import org.dfernandez.smart421.model.Coin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VendorMachineServiceImpl implements VendorMachineService {

    /**
     * Return the least number of coins possible
     *
     * @param pence integer
     * @return Collection of coins(optimal change)
     */
    @Override
    public Collection<Coin> getOptimalChangeFor(int pence) {

        List<Coin> changeReturn = new ArrayList<>();

        int amountLeft = pence;

        // Assume an unlimited supply of coins
        for(Coin coin: Coin.values()){
            while(amountLeft >= coin.getDenomination()) {
                amountLeft -= coin.getDenomination();
                changeReturn.add(coin);
            }
        }
        return changeReturn;
    }
}
