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
        service.getCoinsMap();

        int onePennyCoins = service.getCoinsMap().get(Coin.ONE_PENNY);

        expectedChange.add(Coin.ONE_PENNY);
        expectedChange.add(Coin.ONE_PENNY);
        assertThat(expectedChange, equalTo(service.getChangeFor(2)));

        assertEquals(onePennyCoins-2,(int) FilesUtil.readCoinsValuesFromFile(COIN_INVENTORY_PATH).get(Coin.ONE_PENNY));


    }

    @Test
    public void testChangeForFortyTwoPenceWithoutTwentyPenceAndTwoTenPenceCoins() {

        coins = new HashMap<>();
        coins.put(Coin.ONE_POUND,11);
        coins.put(Coin.FIFTY_PENCE,22);
        coins.put(Coin.TWENTY_PENCE,0);
        coins.put(Coin.TEN_PENCE,2);
        coins.put(Coin.FIVE_PENCE,10);
        coins.put(Coin.TWO_PENCE,10);
        coins.put(Coin.ONE_PENNY,10);

        FilesUtil.writeCoinsValuesToFile(COIN_INVENTORY_PATH, coins);
        service.getCoinsMap();
        expectedChange = new ArrayList<>();
        int tenPenceCoins = service.getCoinsMap().get(Coin.TEN_PENCE);
        int fivePenceCoins = service.getCoinsMap().get(Coin.FIVE_PENCE);
        int twoPenceCoins = service.getCoinsMap().get(Coin.TWO_PENCE);

        expectedChange.add(Coin.TEN_PENCE);
        expectedChange.add(Coin.TEN_PENCE);
        expectedChange.add(Coin.FIVE_PENCE);
        expectedChange.add(Coin.FIVE_PENCE);
        expectedChange.add(Coin.FIVE_PENCE);
        expectedChange.add(Coin.FIVE_PENCE);
        expectedChange.add(Coin.TWO_PENCE);

        assertThat(expectedChange, equalTo(service.getChangeFor(42)));

        assertEquals(tenPenceCoins-2,(int) FilesUtil.readCoinsValuesFromFile(COIN_INVENTORY_PATH).get(Coin.TEN_PENCE));
        assertEquals(fivePenceCoins-4,(int) FilesUtil.readCoinsValuesFromFile(COIN_INVENTORY_PATH).get(Coin.FIVE_PENCE));
        assertEquals(twoPenceCoins-1,(int) FilesUtil.readCoinsValuesFromFile(COIN_INVENTORY_PATH).get(Coin.TWO_PENCE));

    }

    @Test
    public void testChangeForOnePoundWithoutOnePoundAndOneFiftyPenceCoins() {

        coins = new HashMap<>();
        coins.put(Coin.ONE_POUND,0);
        coins.put(Coin.FIFTY_PENCE,1);
        coins.put(Coin.TWENTY_PENCE,10);
        coins.put(Coin.TEN_PENCE,5);
        coins.put(Coin.FIVE_PENCE,0);
        coins.put(Coin.TWO_PENCE,0);
        coins.put(Coin.ONE_PENNY,0);

        FilesUtil.writeCoinsValuesToFile(COIN_INVENTORY_PATH, coins);
        service.getCoinsMap();
        expectedChange = new ArrayList<>();
        int fiftyPenceCoins = service.getCoinsMap().get(Coin.FIFTY_PENCE);
        int twentyPenceCoins = service.getCoinsMap().get(Coin.TWENTY_PENCE);
        int tenPenceCoins = service.getCoinsMap().get(Coin.TEN_PENCE);

        expectedChange.add(Coin.FIFTY_PENCE);
        expectedChange.add(Coin.TWENTY_PENCE);
        expectedChange.add(Coin.TWENTY_PENCE);
        expectedChange.add(Coin.TEN_PENCE);


        assertThat(expectedChange, equalTo(service.getChangeFor(100)));

        assertEquals(fiftyPenceCoins -1,(int) FilesUtil.readCoinsValuesFromFile(COIN_INVENTORY_PATH).get(Coin.FIFTY_PENCE));
        assertEquals(twentyPenceCoins -2,(int) FilesUtil.readCoinsValuesFromFile(COIN_INVENTORY_PATH).get(Coin.TWENTY_PENCE));
        assertEquals(tenPenceCoins -1,(int) FilesUtil.readCoinsValuesFromFile(COIN_INVENTORY_PATH).get(Coin.TEN_PENCE));

    }


    @Test(expected=IllegalArgumentException.class)
    public void testExceptionInsufficientCoinageNoCoins() {

        coins = new HashMap<>();
        coins.put(Coin.ONE_POUND,0);
        coins.put(Coin.FIFTY_PENCE,0);
        coins.put(Coin.TWENTY_PENCE,0);
        coins.put(Coin.TEN_PENCE,0);
        coins.put(Coin.FIVE_PENCE,0);
        coins.put(Coin.TWO_PENCE,0);
        coins.put(Coin.ONE_PENNY,0);

        FilesUtil.writeCoinsValuesToFile(COIN_INVENTORY_PATH, coins);
        service.getCoinsMap();

       service.getChangeFor(10);

    }

    @Test(expected=IllegalArgumentException.class)
    public void testExceptionInsufficientCoinage() {

        coins = new HashMap<>();
        coins.put(Coin.ONE_POUND,0);
        coins.put(Coin.FIFTY_PENCE,10);
        coins.put(Coin.TWENTY_PENCE,0);
        coins.put(Coin.TEN_PENCE,0);
        coins.put(Coin.FIVE_PENCE,0);
        coins.put(Coin.TWO_PENCE,0);
        coins.put(Coin.ONE_PENNY,0);

        FilesUtil.writeCoinsValuesToFile(COIN_INVENTORY_PATH, coins);
        service.getCoinsMap();

        service.getChangeFor(60);

    }
}
