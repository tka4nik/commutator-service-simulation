package com.tka4nik.nexign_task.implementation;

import com.tka4nik.nexign_task.model.CDR;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;

class H2StorageTest {

    @Test
    void basicConsumeMsisdns() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:schema.sql';", "sa", "");
        H2Storage testee = new H2Storage(connection);
        ArrayList<String> msidns = new ArrayList<>();
        msidns.add("79262268435");
        msidns.add("79263336843");
        testee.consume_msisdns(msidns.iterator());
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Abonents");
        ResultSet result = statement.executeQuery();

        result.next();
        Assertions.assertEquals(result.getString("msisdn"), "79262268435");
        System.out.println(result.getString("msisdn"));

        result.next();
        Assertions.assertEquals(result.getString("msisdn"), "79263336843");
        System.out.println(result.getString("msisdn"));
    }

    @Test
    void basicConsumeH2CDRs() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:schema.sql';", "sa", "");
        H2Storage testee = new H2Storage(connection);

        ArrayList<String> msidns = new ArrayList<>();
        msidns.add("79262268435");
        msidns.add("79264678435");
        testee.consume_msisdns(msidns.iterator());

        ArrayList<CDR> cdrs = new ArrayList<>();
        cdrs.add(new CDR(1, "79262268435", 123123123,123123124));
        cdrs.add(new CDR(2, "79264678435", 126123123,126123124));
        testee.consume_cdrs(cdrs.iterator());
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cdr");
        ResultSet result = statement.executeQuery();

        result.next();
        Assertions.assertEquals(result.getString("msisdn"), "79262268435");
        System.out.println(result.getString("msisdn"));

        result.next();
        Assertions.assertEquals(result.getString("msisdn"), "79264678435");
        System.out.println(result.getString("msisdn"));
    }
}