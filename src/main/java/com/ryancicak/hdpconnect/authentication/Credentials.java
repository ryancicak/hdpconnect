package com.ryancicak.hdpconnect.authentication;

/**
 * Created by rcicak on 12/22/16.
 */
public class Credentials {

    // kerberos
    private String krb5Location;
    private String principal;
    private String keytab;

    // simple
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKrb5Location() {
        return krb5Location;
    }

    public void setKrb5Location(String krb5Location) {
        this.krb5Location = krb5Location;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getKeytab() {
        return keytab;
    }

    public void setKeytab(String keytab) {
        this.keytab = keytab;
    }
}
