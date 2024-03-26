package com.tka4nik.nexign_task.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;


class CDRsH2StorageTest  {
    @Test
    void basicCDRsH2Consume() throws SQLException {
        //TODO Создать Connection и вручную SELECT * сделать для проверки
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:schema.sql';", "sa", "");
        CDRsH2Storage testee = new CDRsH2Storage(connection);
        ArrayList<String> msidns = new ArrayList<>();
        msidns.add("79262268435");
        msidns.add("79263336843");
        testee.saveAllmsidns(msidns.iterator());
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Abonents");
        ResultSet result = statement.executeQuery();

        result.next();
        Assertions.assertEquals(result.getString("msisdn"), "79262268435");
        System.out.println(result.getString("msisdn"));

        result.next();
        Assertions.assertEquals(result.getString("msisdn"), "79263336843");
        System.out.println(result.getString("msisdn"));

    }

}