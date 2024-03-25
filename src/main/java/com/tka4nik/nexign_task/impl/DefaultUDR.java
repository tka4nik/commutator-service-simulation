package com.tka4nik.nexign_task.impl;

import com.tka4nik.nexign_task.AllCDRs;
import com.tka4nik.nexign_task.UDR;

class DefaultUDR implements UDR {
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
