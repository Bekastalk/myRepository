package com.example.restjava10.exception;

public class BadCredentialException extends  RuntimeException{ //туура эмес жданныйларды киргизгенде чыкчу

    public BadCredentialException(String message) {
        super(message);
    }
}
