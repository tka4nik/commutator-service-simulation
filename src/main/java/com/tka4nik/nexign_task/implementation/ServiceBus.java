package com.tka4nik.nexign_task.implementation;

//import com.tka4nik.nexign_task.impl.;

import com.tka4nik.nexign_task.model.*;

import java.io.File;
import java.util.List;

public class ServiceBus {
    private final H2Storage h2Storage;

    private ServiceBus(H2Storage h2Storage) {
        this.h2Storage = h2Storage;
    }

    public static ServiceBus create() {
        try {
            return new ServiceBus(new H2Storage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CDRsSupplier createCDRsGenerator(List<String> msisdns) {
        return new RandomCDRsGenerator(msisdns);
    }

    public MSISDNsConsumer getMsidnsConsumer() {
        return this.h2Storage;
    }

    public MSISDNsSupplier getMSISDNsGenerator() {
        return new RandomMSISDNsGenerator();
    }

    public CDRsConsumer createFileStorage(File file) {
        return new CDRsFileStorage(file);
    }

    public CDRsConsumer getH2Storage() {
        return this.h2Storage;
    }

    public UDR createUDR(AllCDRs cdrs) {
        return null;
    }
}
