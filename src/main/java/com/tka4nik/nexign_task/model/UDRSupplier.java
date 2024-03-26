package com.tka4nik.nexign_task.model;

public interface UDRSupplier {
    void generateReport();

    void generateReport(String msisdn);

    void generateReport(String msisdn, int month);
}
