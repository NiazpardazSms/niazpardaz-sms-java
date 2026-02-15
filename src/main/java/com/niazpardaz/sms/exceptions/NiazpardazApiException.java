package com.niazpardaz.sms.exceptions;

/**
 * خطای منطقی API (مثل اعتبار ناکافی، شماره نامعتبر)
 */
public class NiazpardazApiException extends NiazpardazException {
    private final int errorCode;

    public NiazpardazApiException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public NiazpardazApiException(String message, int errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() { return errorCode; }
}
