package com.tka4nik.nexign_task.model;

public interface UDRsConsumer {
    void generateReport();

    void generateReport(String msisdn);

    void generateReport(String msisdn, int month);
}
