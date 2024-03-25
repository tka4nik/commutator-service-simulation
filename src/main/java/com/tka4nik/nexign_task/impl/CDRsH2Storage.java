package com.tka4nik.nexign_task.impl;

import com.tka4nik.nexign_task.CDR;
import com.tka4nik.nexign_task.CDRsConsumer;
import com.tka4nik.nexign_task.MsidnsConsumer;

import java.sql.*;
import java.util.Iterator;

class CDRsH2Storage implements CDRsConsumer, MsidnsConsumer {
    private final Connection connection;

    public CDRsH2Storage() throws SQLException {
        this(DriverManager.getConnection("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:schema.sql';", "sa", ""));
    }

    CDRsH2Storage(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveAllmsidns(Iterator<String> msidns) {
        try {
            while (msidns.hasNext()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Abonents (msisdn) VALUES (?)");
                statement.setString(1, msidns.next());
                statement.execute();
//                msidns.next()
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void consume(Iterator<CDR> iterator) {
//        try {
//
////            Statement statement = connection.prepareStatement("")
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
