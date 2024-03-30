package com.tka4nik.nexign_task;

import com.tka4nik.nexign_task.implementation.ServiceBus;
import com.tka4nik.nexign_task.model.*;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        ServiceBus factory = ServiceBus.create();
        doTask(factory);
    }

    public static void doTask(ServiceBus factory) {
        String filepath_cdr = "./cdr/";
        String filepath_udr = "./reports/";
        createCDRs(factory, filepath_cdr);
        createUDRs(factory, filepath_cdr, filepath_udr);
    }

    public static void createCDRs(ServiceBus factory, String filepath) {
        MSISDNsSupplier msisdnsGenerator = factory.getMSISDNsGenerator();
        List<String> msisdns = msisdnsGenerator.supplyMsisdns();

        factory.getMsidnsConsumer().consumeMsisdns(msisdns.iterator());

        CDRsSupplier generator = factory.getCDRsGenerator(msisdns);
        CDRsConsumer h2Storage = factory.getH2Storage();
        for (int i = 1; i <= 12; i++) {
            CDRsConsumer fileStorage = factory.getFileStorage(new File(String.format(filepath + "%d_cdr.txt", i)));

            Iterator<CDR> cdrs = generator.supply(i);
            fileStorage.consumeCdrs(cdrs);
            h2Storage.consumeCdrs(cdrs);
        }
    }

    public static void createUDRs(ServiceBus factory, String filepath_cdr, String filepath_udr) {
        UDRsSupplier udrsSupplier = factory.getUDRSupplier(filepath_cdr);
        factory.getUDRConsumer(filepath_udr, udrsSupplier.supply()).generateReport();

    }
}


