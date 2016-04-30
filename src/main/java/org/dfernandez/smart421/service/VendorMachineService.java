package org.dfernandez.smart421.service;

import org.dfernandez.smart421.model.Coin;

import java.util.Collection;


public interface VendorMachineService {

    public Collection<Coin> getOptimalChangeFor(int pence);

}
