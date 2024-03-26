package com.tka4nik.nexign_task;

import com.tka4nik.nexign_task.implementation.ServiceBus;
import com.tka4nik.nexign_task.model.*;

import java.io.File;
import java.util.Iterator;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ServiceBus factory = ServiceBus.create();
        doTask(factory);
    }

    public static void doTask(ServiceBus factory) {
        AllCDRs db = createCDRs(factory);
//        consumer.consume(db.getAllCDRs());
    }

    public static AllCDRs createCDRs(ServiceBus factory) {
        MSISDNsSupplier msisdnsGenerator = factory.getMSISDNsGenerator();
        List<String> msisdns = msisdnsGenerator.supplyMsisdns();

        factory.getMsidnsConsumer().consumeMsisdns(msisdns.iterator());

        CDRsSupplier generator = factory.getCDRsGenerator(msisdns);
        CDRsConsumer h2Storage = factory.getH2Storage();
        for (int i = 1; i <= 12; i++) {
            CDRsConsumer fileStorage = factory.getFileStorage(new File(String.format("./cdr/%d_cdr.txt", i)));

            Iterator<CDR> cdrs = generator.supply(i);
            fileStorage.consumeCdrs(cdrs);
            h2Storage.consumeCdrs(cdrs);
        }
        return null;
    }
}


