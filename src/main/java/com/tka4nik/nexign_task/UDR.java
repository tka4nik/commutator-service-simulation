package com.tka4nik.nexign_task;

public interface UDR {
    void generateReport();

    void generateReport(String msisdn);

    void generateReport(String msisdn, int month);
}
