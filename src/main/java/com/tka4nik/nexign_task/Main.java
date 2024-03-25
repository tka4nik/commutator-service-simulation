package com.tka4nik.nexign_task;

import com.tka4nik.nexign_task.impl.ServiceBus;

import java.io.File;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        CDRService cdr = new CDRService();
        cdr.generate_cdr();
    }

    public AllCDRs createCDRs(ServiceBus factory) {
        ArrayList<String> msisdns = new ArrayList<String>();
        // msisdn supplier?
        //TODO: Generate
        factory.getMsidnsConsumer().saveAllmsidns(msisdns.iterator());

        CDRsSupplier generator = factory.createRandomGenerator(msisdns);
        for (int i = 0; i <= 12; i++) {
            CDRsConsumer file_storage = factory.createFileStorage(new File(String.format("%d_cdr", i)));
            file_storage.consume(generator.supply()); //TODO: Implement

        }
        return null;
    }
    public void doTask(ServiceBus factory) {
//        CDRsConsumer file_storage = factory.createFileStorage();
        AllCDRs db = createCDRs(factory);
        CDRsConsumer consumer = factory.getH2Storage();
//        consumer.consume(db.getAllCDRs());

    }
}


