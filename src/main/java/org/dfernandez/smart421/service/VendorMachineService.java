package org.dfernandez.smart421.service;

import org.dfernandez.smart421.exception.InsufficientCoinageException;
import org.dfernandez.smart421.model.Coin;

import java.util.Collection;
import java.util.Map;


public interface VendorMachineService {

    public Collection<Coin> getOptimalChangeFor(int pence);

    public Collection<Coin> getChangeFor(int pence) throws InsufficientCoinageException;

    public Map<Coin, Integer> getCoinsMap();

}
