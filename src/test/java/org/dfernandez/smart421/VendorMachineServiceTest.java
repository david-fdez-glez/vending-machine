package org.dfernandez.smart421;

import org.dfernandez.smart421.model.Coin;
import org.dfernandez.smart421.service.VendorMachineService;
import org.dfernandez.smart421.service.VendorMachineServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;



public class VendorMachineServiceTest {

    VendorMachineService service;
    List<Coin> expectedChange;

    @Before
    public void init() {
       service = new VendorMachineServiceImpl();
    }


    @Test
    public void testChangeForZeroPence() {

        expectedChange = new ArrayList<>();

        assertThat(expectedChange, equalTo(service.getOptimalChangeFor(0)));
    }

    @Test
    public void testChangeForOnePenny() {
        expectedChange = new ArrayList<>();
        expectedChange.add(Coin.ONE_PENNY);

        assertThat(expectedChange, equalTo(service.getOptimalChangeFor(1)));
    }

    @Test
    public void testChangeForTwoPence() {
        expectedChange = new ArrayList<>();
        expectedChange.add(Coin.TWO_PENCE);

        assertThat(expectedChange, equalTo(service.getOptimalChangeFor(2)));
    }
}
