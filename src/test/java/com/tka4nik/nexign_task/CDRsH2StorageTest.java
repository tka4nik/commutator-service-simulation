package com.tka4nik.nexign_task;

import com.tka4nik.nexign_task.impl.CDRsH2Storage;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

class CDRsH2StorageTest {
    @Test
    void test() throws SQLException {
        //TODO Создать Connection и вручную SELECT * сделать для проверки
        CDRsH2Storage testee = new CDRsH2Storage();
        ArrayList<String> msidns = new ArrayList<>();
        msidns.add("79262268435");
        msidns.add("79263336843");
        testee.saveAllmsidns(msidns.iterator());
    }


}