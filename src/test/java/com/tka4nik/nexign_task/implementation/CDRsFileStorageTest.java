package com.tka4nik.nexign_task.implementation;

import com.tka4nik.nexign_task.model.CDR;
import com.tka4nik.nexign_task.model.CDRsConsumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


class CDRsFileStorageTest {
    @Test
    void basicFileStorageConsumer() throws IOException {
        ServiceBus factory = ServiceBus.create();
        CDRsConsumer fileStorage = factory.getFileStorage(new File(String.format("./test_cdr/%d_cdr.txt", 1)));

        ArrayList<CDR> cdrs = new ArrayList<>();
        cdrs.add(new CDR(1, "79262268435", 123123123, 123123156));

        fileStorage.consumeCdrs(cdrs.iterator());
        Assertions.assertTrue(new File("./test_cdr/1_cdr.txt").exists());
        String content = Files.readString(Paths.get("./test_cdr/1_cdr.txt"));
        System.out.println(content);
        Assertions.assertTrue(content.equals("01,79262268435,123123123,123123156\n"));
    }

}