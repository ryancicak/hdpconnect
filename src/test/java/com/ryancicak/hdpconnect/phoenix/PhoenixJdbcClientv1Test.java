package com.ryancicak.hdpconnect.phoenix;

import com.ryancicak.hdpconnect.authentication.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

public class PhoenixJdbcClientv1Test {
    private static Logger logger = Logger.getLogger(PhoenixJdbcClientv1Test.class);


    @Test
    public void createConnection() throws IOException, SQLException {

        PhoenixJdbcClientv1 phoenix = PhoenixJdbcClientv1Impl.getInstance();

        Credentials credentials = new Credentials();
        //credentials.setUser("hbase");
        credentials.setPrincipal("hbase-ryancicak@RYAN.COM");
        credentials.setKeytab("/users/rcicak/Desktop/hbase.headless.keytab");
        phoenix.createConnection("jdbc:phoenix:rcicakm2.field.hortonworks.com,rcicakm0.field.hortonworks.com,rcicakm1.field.hortonworks.com:2181:/hbase-secure", credentials);

        Connection con = phoenix.getConnection();
        Statement stmt = con.createStatement();

        String sql = ("select * from rupert");
        ResultSet res = stmt.executeQuery(sql);
        while(res.next()) {
            logger.info(res.getInt(1) + "," + res.getString(2));
        }
        stmt.close();

    }

}