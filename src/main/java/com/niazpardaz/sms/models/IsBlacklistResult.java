package com.niazpardaz.sms.models;

/**
 * نتیجه بررسی شماره در لیست سیاه
 */
public class IsBlacklistResult {
    private boolean isBlack;
    private int resultCode;

    public boolean isBlack() { return isBlack; }
    public void setBlack(boolean black) { isBlack = black; }

    public int getResultCode() { return resultCode; }
    public void setResultCode(int resultCode) { this.resultCode = resultCode; }
}
