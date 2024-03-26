package com.tka4nik.nexign_task.implementation;

import com.tka4nik.nexign_task.model.CDR;
import com.tka4nik.nexign_task.model.CDRsConsumer;
import com.tka4nik.nexign_task.model.MSISDNsConsumer;

import java.sql.*;
import java.util.Iterator;

class H2Storage implements CDRsConsumer, MSISDNsConsumer {
    private final Connection connection;

    public H2Storage() throws SQLException {
        this(DriverManager.getConnection("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:schema.sql';", "sa", ""));
    }

    H2Storage(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void consume_msisdns(Iterator<String> msidns) {
        try {
            while (msidns.hasNext()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Abonents (msisdn) VALUES (?)");
                statement.setString(1, msidns.next());
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void consume_cdrs(Iterator<CDR> cdrs) {
        try {
            while (cdrs.hasNext()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Cdr (call_type, msisdn, start_time, end_time) VALUES (?, ?, ?, ?)");
                CDR cdr = cdrs.next();
                statement.setInt(1, cdr.getCallType());
                statement.setString(2, cdr.getMsisdn());
                statement.setLong(3, cdr.getStartTime());
                statement.setLong(4, cdr.getEndTime());
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
