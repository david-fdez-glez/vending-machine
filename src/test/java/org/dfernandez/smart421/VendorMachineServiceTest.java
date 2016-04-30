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
    public void testChangeForZero() {

        expectedChange = new ArrayList<>();

        assertThat(expectedChange, equalTo(service.getOptimalChangeFor(0)));
    }
}
