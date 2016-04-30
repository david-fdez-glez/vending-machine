package org.dfernandez.smart421;

import org.dfernandez.smart421.model.Coin;
import org.dfernandez.smart421.service.VendorMachineService;
import org.dfernandez.smart421.service.VendorMachineServiceImpl;
import org.dfernandez.smart421.util.FilesUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class VendorMachineInventoryChangeTest {

    static final String COIN_INVENTORY_PATH = "src/test/resources/coin-inventory.properties";

    VendorMachineService service;
    List<Coin> expectedChange;
    Map<Coin, Integer> coins;

    @Before
    public void init() {
       service = new VendorMachineServiceImpl(COIN_INVENTORY_PATH);
       coins = new HashMap<>();
    }


    @Test
    public void testChangeForZeroPence() {
        expectedChange = new ArrayList<>();
        assertThat(expectedChange, equalTo(service.getChangeFor(0)));
    }


    @Test
    public void testChangeForFivePenceWithoutTwoPenceCoins() {

        coins = new HashMap<>();
        coins.put(Coin.ONE_POUND,11);
        coins.put(Coin.FIFTY_PENCE,22);
        coins.put(Coin.TWENTY_PENCE,33);
        coins.put(Coin.TEN_PENCE,44);
        coins.put(Coin.FIVE_PENCE,55);
        coins.put(Coin.TWO_PENCE,0);
        coins.put(Coin.ONE_PENNY,77);

        FilesUtil.writeCoinsValuesToFile(COIN_INVENTORY_PATH, coins);
        expectedChange = new ArrayList<>();
        int onePennyCoins = service.getCoinsMap().get(Coin.ONE_PENNY);

        expectedChange.add(Coin.ONE_PENNY);
        expectedChange.add(Coin.ONE_PENNY);
        assertThat(expectedChange, equalTo(service.getChangeFor(2)));

        assertEquals(onePennyCoins-2,(int) FilesUtil.readCoinsValuesFromFile(COIN_INVENTORY_PATH).get(Coin.ONE_PENNY));


    }

    /*
    @Test
    public void testChangeForFivePence() {
        expectedChange = new ArrayList<>();
        expectedChange.add(Coin.FIVE_PENCE);
        assertThat(expectedChange, equalTo(service.getOptimalChangeFor(5)));
    }


    @Test
    public void testChangeForTwentyThreePence() {
        expectedChange = new ArrayList<>();
        expectedChange.add(Coin.TWENTY_PENCE);
        expectedChange.add(Coin.TWO_PENCE);
        expectedChange.add(Coin.ONE_PENNY);
        assertThat(expectedChange, equalTo(service.getOptimalChangeFor(23)));
    }

    @Test
    public void testChangeForOnePoundOnePenny() {
        expectedChange = new ArrayList<>();
        expectedChange.add(Coin.ONE_POUND);
        expectedChange.add(Coin.ONE_PENNY);
        assertThat(expectedChange, equalTo(service.getOptimalChangeFor(101)));
    }
    */
}
