package com.tka4nik.nexign_task;

import com.tka4nik.nexign_task.implementation.ServiceBus;
import com.tka4nik.nexign_task.model.AllCDRs;
import com.tka4nik.nexign_task.model.CDRsConsumer;
import com.tka4nik.nexign_task.model.CDRsSupplier;
import com.tka4nik.nexign_task.model.MSISDNsSupplier;

import java.io.File;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ServiceBus factory = ServiceBus.create();
        doTask(factory);
    }

    public static void doTask(ServiceBus factory) {
        AllCDRs db = createCDRs(factory);
        CDRsConsumer consumer = factory.getH2Storage();
//        consumer.consume(db.getAllCDRs());
    }

    public static AllCDRs createCDRs(ServiceBus factory) {
        MSISDNsSupplier msisdns_generator = factory.getMSISDNsGenerator();
        List<String> msisdns = msisdns_generator.supply_msisdns();

        factory.getMsidnsConsumer().consume_msisdns(msisdns.iterator());

        CDRsSupplier generator = factory.createCDRsGenerator(msisdns);
        for (int i = 1; i <= 12; i++) {
            CDRsConsumer file_storage = factory.createFileStorage(new File(String.format("./cdr/%d_cdr", i)));
            file_storage.consume_cdrs(generator.supply(i));

        }
        return null;
    }
}


