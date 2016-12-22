package com.ryancicak.hdpconnect.phoenix;

import com.ryancicak.hdpconnect.authentication.Credentials;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Copyright 2016 Ryan Cicak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * PhoenixJdbcClientv1 verified with HDP 2.4.2 (notice pom.xml referenses 4.4.0-HBase-1.1 and Hadoop 2.7.1)
 *
 * In order for PhoenixJdbcClientv1 to work, you'll need to import your core-site.xml, hbase-site.xml and hdfs-site.xml
 * into the resources folder.  Install Kerberos client (ensuring /etc/krb5.conf) for the configured realm of the keytab
 *
 * If you're receiving Kerberos errors with "Mechanism level: Server not found in Kerberos database (7) - LOOKING_UP_SERVER",
 * you'll need to update your /etc/hosts with the proper FQDN/IP for the HBase master and HBase region servers.
 * Please see http://web.mit.edu/KERBEROS/krb5-1.4/krb5-1.4.1/doc/krb5-admin/Getting-DNS-Information-Correct.html
 * Created by rcicak on 12/22/16.
 */
public interface PhoenixJdbcClientv1 {


    public static final String driverName = "org.apache.phoenix.jdbc.PhoenixDriver";

    // url example: Kerberos = jdbc:phoenix:host1,host2,host3:2181:/hbase-secure
    // url example: Simple = Kerberos = jdbc:phoenix:host1,host2,host3:2181:/hbase-unsecure
    void createConnection(String url, Credentials credentials) throws SQLException, IOException;
    Connection getConnection();
    void disconnect() throws SQLException;

}
