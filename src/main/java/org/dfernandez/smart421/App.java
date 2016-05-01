package org.dfernandez.smart421;

import org.dfernandez.smart421.exception.InsufficientCoinageException;
import org.dfernandez.smart421.model.Coin;
import org.dfernandez.smart421.service.VendorMachineService;
import org.dfernandez.smart421.service.VendorMachineServiceImpl;
import org.dfernandez.smart421.util.Constants;

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

        //try {
            VendorMachineService vendorMachineServicePart1 = new VendorMachineServiceImpl();
            VendorMachineService vendorMachineServicePart2 = new VendorMachineServiceImpl(Constants.COIN_INVENTORY_PATH);

            Collection<Coin> coinsResultList;

            System.out.println( "Welcome to our Vending Machine" );
            System.out.println( "Type number of pence to see the optimal change. (Any character will exit)" );

            Scanner in = new Scanner(System.in);

            int entry;


            while(in.hasNextInt()) {
               try {
                   entry = in.nextInt();

                   coinsResultList = vendorMachineServicePart1.getOptimalChangeFor(entry);
                   System.out.println( "Part 1: Optimal Change for " +entry + " pence is " +coinsResultList);
                   coinsResultList = vendorMachineServicePart2.getChangeFor(entry);
                   System.out.println( "Part 2: Optimal Change for " +entry + " pence is " +coinsResultList);

               }  catch (InsufficientCoinageException exception){
                   System.out.println("Part 2: " + exception.getMessage());
               }
               System.out.println("\nType number of pence to see the optimal change. (Any character will exit)" );


            }
       // } catch (InsufficientCoinageException exception) {
       //     System.out.println("Part 2: " +exception.getMessage());
       // }
    }
}
