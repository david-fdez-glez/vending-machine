package org.dfernandez.smart421.service;


import org.dfernandez.smart421.model.Coin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VendorMachineServiceImpl implements VendorMachineService {

    @Override
    public Collection<Coin> getOptimalChangeFor(int pence) {

        List<Coin> changeList = new ArrayList<>();

        int amountLeft = pence;

        for(Coin coin: Coin.values()){

            while(amountLeft >= coin.getDenomination()) {
                amountLeft -= coin.getDenomination();
                changeList.add(coin);
            }
        }
        return changeList;
    }
}
