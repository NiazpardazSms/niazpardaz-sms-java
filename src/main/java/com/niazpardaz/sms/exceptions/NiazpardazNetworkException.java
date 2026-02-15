package com.niazpardaz.sms.exceptions;

/**
 * خطای شبکه و HTTP (مثل timeout، DNS error)
 */
public class NiazpardazNetworkException extends NiazpardazException {
    private final int statusCode;

    public NiazpardazNetworkException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public NiazpardazNetworkException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = 0;
    }

    public int getStatusCode() { return statusCode; }
}
