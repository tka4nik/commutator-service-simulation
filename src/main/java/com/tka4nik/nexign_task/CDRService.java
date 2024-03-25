package com.tka4nik.nexign_task;


import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CDRService implements ICDRService {
    private static final int cdr_number = 12;
    private static final int max_number_of_calls = 30;
    private static final int min_number_of_calls = 5;
    private static final Random random = new Random();
    private static final int abonents_number = random.nextInt(20) + 10;

    private List<Abonent> abonents = new ArrayList<>();
    public CDRService() {
        this.abonents = generate_abonents(abonents_number);
    }

    @Override
    public void generate_cdr() {
        System.out.println(abonents);
        for (int i = 1; i <= cdr_number; i++) {
            String filepath = String.format("./cdr/%d_cdr.txt", i);

            try {
                FileWriter fileWriter = new FileWriter(filepath);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                int number_of_calls = random.nextInt(max_number_of_calls) + min_number_of_calls;
                for (int j = 0; j < number_of_calls; j++) {
                    Abonent abonent = abonents.get(random.nextInt(0,abonents.size()));
                    String cdr = abonent.generate_call_cdr(i);
                    bufferedWriter.write(cdr);
                }
                bufferedWriter.close();
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public List<Abonent> generate_abonents(int n) {
        int current_number = 0;
        while (current_number < n) {
            String telephone = generate_telephone_number();
            Abonent abonent = new Abonent(telephone);

            if (!abonents.contains(abonent))  {
                System.out.println(telephone);
                abonents.add(abonent);
                current_number += 1;
            }
        }
        return abonents;
    }

    private String generate_telephone_number() {
        int countryCode = 7;
        int operatorCode = random.nextInt(900) + 100; // от 100 до 999
        int subscriberNumber = random.nextInt(9000000) + 1000000; // от 1000000 до 9999999

        return String.format("%01d%03d%07d", countryCode, operatorCode, subscriberNumber);
    }
}
