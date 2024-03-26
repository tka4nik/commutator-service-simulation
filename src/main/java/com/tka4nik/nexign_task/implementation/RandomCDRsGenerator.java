package com.tka4nik.nexign_task.implementation;

import com.tka4nik.nexign_task.model.CDR;
import com.tka4nik.nexign_task.model.CDRsSupplier;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

class RandomCDRsGenerator implements CDRsSupplier {
    private static final Random random = new Random();
    
    private final List<String> msisdns;
    RandomCDRsGenerator(List<String> msisdns) {
        this.msisdns = msisdns;
    }

    @Override
    public Iterator<CDR> supply(int month) {
        ArrayList<CDR> cdrs = new ArrayList<>();
        
        int number_of_calls = random.nextInt(30) + 5;
        for (int j = 0; j < number_of_calls; j++) {
            cdrs.add(generate_cdr(month));
        }
        return cdrs.iterator();
    }
    
    public CDR generate_cdr(int month) {
        int call_type = random.nextInt(1, 2+1);
        String msisdn = msisdns.get(random.nextInt(0,msisdns.size()));
        long start_time = generate_random_start_time(month);
        long duration = random.nextLong(10, 300 + 1);
        long end_time = start_time + duration;
        return new CDR(call_type, msisdn, start_time, end_time);
    }

    private long generate_random_start_time(int month) {
        LocalDateTime start_of_month = LocalDateTime.of(2024, month, 1, 0, 0);
        long start_of_month_unix = start_of_month.toEpochSecond(ZoneOffset.UTC);
        long end_of_month = LocalDateTime.of(2024, month, start_of_month.getMonth().length(start_of_month.toLocalDate().isLeapYear()), 23, 59, 59).toEpochSecond(ZoneOffset.UTC);
        return random.nextLong(start_of_month_unix, end_of_month + 1);
    }
}
