package com.tka4nik.nexign_task.implementation;

import com.tka4nik.nexign_task.model.CDR;
import com.tka4nik.nexign_task.model.CDRsConsumer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

class CDRsFileStorage implements CDRsConsumer {
    private final File file;

    CDRsFileStorage(File file) {
        this.file = file;
    }

    @Override
    public void consume_cdrs(Iterator<CDR> iterator) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            while (iterator.hasNext()) {
                CDR cdr = iterator.next();
                bufferedWriter.write(cdr.toString());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
