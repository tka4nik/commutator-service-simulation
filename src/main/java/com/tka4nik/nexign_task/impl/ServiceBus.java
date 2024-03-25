package com.tka4nik.nexign_task.impl;

//import com.tka4nik.nexign_task.impl.;

import com.tka4nik.nexign_task.*;

import java.io.File;
import java.util.List;

public class ServiceBus {
    private final CDRsH2Storage cdrsH2Storage;

    private ServiceBus(CDRsH2Storage cdrsH2Storage) {
        this.cdrsH2Storage = cdrsH2Storage;
    }
    //FabricMethod

    public static ServiceBus create() {
        try {
            return new ServiceBus(new CDRsH2Storage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    CDRsConsumer createFileStorage(File file) {
        return null;
    }

    CDRsConsumer getH2Storage() {
        return this.cdrsH2Storage;
    }

    CDRsSupplier createRandomGenerator(List<String> msisdns) {
        return null;
    }

    UDR createUDR(AllCDRs cdrs) {
        return null;
    }

    MsidnsConsumer getMsidnsConsumer() {
        return this.cdrsH2Storage;
    }
}
