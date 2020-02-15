package com.wideoapp.WideoAppSecurity.helloworld;

import java.io.Serializable;

public class Testowa implements Serializable {

    private String odp;

    public Testowa(String odp) {
        this.odp = odp;
    }

    public String getOdp() {
        return odp;
    }
}
