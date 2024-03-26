package com.tka4nik.nexign_task.implementation;

import com.tka4nik.nexign_task.model.AllCDRs;
import com.tka4nik.nexign_task.model.UDRSupplier;

class DefaultUDR implements UDRSupplier {
    private final AllCDRs cdrs;

    DefaultUDR(AllCDRs cdrs) {
        this.cdrs = cdrs;
    }

    @Override
    public void generateReport() {
        // по всем cdr проходим в map
    }

    @Override
    public void generateReport(String msisdn) {

    }

    @Override
    public void generateReport(String msisdn, int month) {

    }
}
