package org.dfernandez.smart421;

import org.dfernandez.smart421.model.Coin;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CoinTest {

    @Before
    public void init() {

    }

    @Test
    public void testCoinValueOnePound() throws Exception {
        assertEquals(Coin.ONE_POUND.getDenomination(),100);
    }

    @Test
    public void testCoinValueFiftyPence() throws Exception {
        assertEquals(Coin.FIFTY_PENCE.getDenomination(),50);
    }

    @Test
    public void testCoinValueTwentyPence() throws Exception {
        assertEquals(Coin.TWENTY_PENCE.getDenomination(),20);
    }

    @Test
    public void testCoinValueTenPence() throws Exception {
        assertEquals(Coin.TEN_PENCE.getDenomination(),10);
    }

    @Test
    public void testCoinValueFivePence() throws Exception {
        assertEquals(Coin.FIVE_PENCE.getDenomination(),5);
    }


    @Test
    public void testCoinValueTwoPence() throws Exception {
        assertEquals(Coin.TWO_PENCE.getDenomination(),2);
    }


    @Test
    public void testCoinValueOnePenny() throws Exception {
        assertEquals(Coin.ONE_PENNY.getDenomination(),1);
    }
}
