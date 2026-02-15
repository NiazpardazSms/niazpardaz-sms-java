package com.niazpardaz.sms.models;

/**
 * نتیجه بررسی محتوای پیامک
 */
public class CheckContentResult {
    private boolean isValid;
    private int resultCode;

    public boolean isValid() { return isValid; }
    public void setValid(boolean valid) { isValid = valid; }

    public int getResultCode() { return resultCode; }
    public void setResultCode(int resultCode) { this.resultCode = resultCode; }
}
