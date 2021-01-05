package com.wideoapp.WideoAppSecurity.Web.Model;

import java.io.Serializable;

public class ResponseMessage implements Serializable {

    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
