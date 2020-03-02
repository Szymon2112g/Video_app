package com.wideoapp.WideoAppDatabase.Controller.Model;

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
