package com.tka4nik.nexign_task.implementation;

import com.tka4nik.nexign_task.model.MSISDNsSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that generates random MSISDNs (Mobile Station International Subscriber Directory Number).
 * Implements MSISDNsSupplier interface to provide a list of randomly generated MSISDNs.
 */
public class RandomMSISDNsGenerator implements MSISDNsSupplier {
    private static final Random random = new Random();

    /**
     * Supplies a list of randomly generated unique MSISDNs.
     *
     * @return List of randomly generated unique MSISDNs.
     */
    @Override
    public List<String> supplyMsisdns() {
        ArrayList<String> msisdns = new ArrayList<>();
        int current_number = 0;

        while (current_number < 10) {
            String telephone = generateTelephoneNumber();

            // Check for uniqueness
            if (!msisdns.contains(telephone))  {
                msisdns.add(telephone);
                current_number += 1;
                System.out.println(telephone);
            }
        }
        return msisdns;
    }

    private String generateTelephoneNumber() {
        int countryCode = 7;
        int operatorCode = random.nextInt(900) + 100; // от 100 до 999
        int subscriberNumber = random.nextInt(9000000) + 1000000; // от 1000000 до 9999999

        return String.format("%01d%03d%07d", countryCode, operatorCode, subscriberNumber);
    }
}
