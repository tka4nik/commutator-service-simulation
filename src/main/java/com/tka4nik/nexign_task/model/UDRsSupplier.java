package com.tka4nik.nexign_task.model;

public interface UDRsSupplier {
    void generateReport();

    void generateReport(String msisdn);

    void generateReport(String msisdn, int month);
}
