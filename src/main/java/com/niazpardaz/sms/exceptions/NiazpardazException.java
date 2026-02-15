package com.niazpardaz.sms.exceptions;

/**
 * کلاس پایه استثناهای SDK نیازپرداز
 */
public class NiazpardazException extends RuntimeException {
    public NiazpardazException(String message) {
        super(message);
    }

    public NiazpardazException(String message, Throwable cause) {
        super(message, cause);
    }
}
