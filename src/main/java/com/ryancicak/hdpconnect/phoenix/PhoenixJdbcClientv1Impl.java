package com.ryancicak.hdpconnect.phoenix;

import com.ryancicak.hdpconnect.authentication.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by rcicak on 12/22/16.
 */
public class PhoenixJdbcClientv1Impl implements PhoenixJdbcClientv1 {

    static PhoenixJdbcClientv1Impl singleton = null;
    private static Logger logger = Logger.getLogger(PhoenixJdbcClientv1Impl.class);
    private static Connection connection = null;

    private PhoenixJdbcClientv1Impl() {}

    public static PhoenixJdbcClientv1Impl getInstance() {

        if(singleton == null) {
            singleton = new PhoenixJdbcClientv1Impl();
        }
        return singleton;

    }

    /**
     * Connect to Phoenix
     * @param url connect string
     * @param credentials simple or kerberos
     * @throws SQLException
     */
    public void createConnection(String url, Credentials credentials) throws SQLException, IOException {

        logger.debug("Creating authentication credentials...");

        authenticate(credentials);

        logger.debug("Authentication complete");

        logger.debug("Creating connection...");

        connection = DriverManager.getConnection(url);

        logger.debug("Connection created");

    }

    /**
     * Disconnect the connection
     * @throws SQLException
     */
    public void disconnect() throws SQLException {

        if(connection != null) {

            logger.debug("Closing connection...");

            connection.close();

            logger.debug("Closed connection");

        }
    }

    /**
     * Authenticate either simple (user) or kerberos (prinicpal/keytab)
     * @param credentials
     * @throws IOException
     */
    private void authenticate(Credentials credentials) throws IOException {

        // simple
        String user = null;
        // kerberos
        String keytab = null;
        String principal = null;

        // simple
        if((user = credentials.getUser()) != null) {

            UserGroupInformation ugi = UserGroupInformation.createRemoteUser(user);
            UserGroupInformation.setLoginUser(ugi);

            // kerberos
        } else if((keytab = credentials.getKeytab()) != null && (principal = credentials.getPrincipal()) != null) {

            String krb5ConfigFilePath =
                    (credentials.getKrb5Location() == null ? "/etc/krb5.conf" : credentials.getKrb5Location());
            System.setProperty("java.security.krb5.conf", krb5ConfigFilePath);

            UserGroupInformation.loginUserFromKeytab(principal, keytab);

        } else {
            logger.error("Please fill out the Credentials! Simple or Kerberos");
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
