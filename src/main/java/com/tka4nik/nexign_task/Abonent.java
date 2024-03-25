package com.tka4nik.nexign_task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

public class Abonent {
    public String telephone_number;
    public Abonent(String telephoneNumber) {
        this.telephone_number = telephoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Abonent abonent))
            return false;
        return this.telephone_number.equals(abonent.telephone_number);
    }

    private static final Random random = new Random();

    public String generate_call_cdr(int month) {
        long call_type = random.nextInt(1, 2+1);
        long start_time = generate_random_start_time(month);
        long duration = random.nextLong(10, 300 + 1);
        long end_time = start_time + duration;
        return String.format("%02d,%s,%d,%d%n", call_type, this.telephone_number, start_time, end_time);

    }

    private long generate_random_start_time(int month) {
        LocalDateTime start_of_month = LocalDateTime.of(2024, month, 1, 0, 0);
        long start_of_month_unix = start_of_month.toEpochSecond(ZoneOffset.UTC);
        long end_of_month = LocalDateTime.of(2024, month, start_of_month.getMonth().length(start_of_month.toLocalDate().isLeapYear()), 23, 59, 59).toEpochSecond(ZoneOffset.UTC);
        return random.nextLong(start_of_month_unix, end_of_month + 1);
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }
}
