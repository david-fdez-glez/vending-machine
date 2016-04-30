package org.dfernandez.smart421;

import org.dfernandez.smart421.model.Coin;
import org.dfernandez.smart421.service.VendorMachineService;
import org.dfernandez.smart421.service.VendorMachineServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        VendorMachineService vendorMachineService = new VendorMachineServiceImpl();
        Collection<Coin> coinsResultList;

        System.out.println( "Welcome to our Vending Machine" );
        System.out.println( "Type number of pence to see the optimal change. (Any character will exit)" );

        Scanner in = new Scanner(System.in);

        int entry;


        while(in.hasNextInt()) {

            entry = in.nextInt();


            coinsResultList = vendorMachineService.getOptimalChangeFor(entry);
            System.out.println( "Optimal Change for " +entry + " pence is " +coinsResultList);
            System.out.println("\n");

            System.out.println( "Type number of pence to see the optimal change. (Any character will exit)" );
        }

    }
}
