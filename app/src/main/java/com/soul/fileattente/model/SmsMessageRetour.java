package com.soul.fileattente.model;

import java.io.Serializable;

public class SmsMessageRetour implements Serializable {
    String message;

    public SmsMessageRetour() {
    }

    public SmsMessageRetour(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
