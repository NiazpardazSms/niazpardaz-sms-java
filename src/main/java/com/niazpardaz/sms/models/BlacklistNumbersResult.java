package com.niazpardaz.sms.models;

import java.util.Collections;
import java.util.List;

/**
 * نتیجه استخراج شماره‌های لیست سیاه
 */
public class BlacklistNumbersResult {
    private List<String> blackListNumbers;
    private int resultCode;

    public List<String> getBlackListNumbers() {
        return blackListNumbers != null ? blackListNumbers : Collections.emptyList();
    }
    public void setBlackListNumbers(List<String> blackListNumbers) { this.blackListNumbers = blackListNumbers; }

    public int getResultCode() { return resultCode; }
    public void setResultCode(int resultCode) { this.resultCode = resultCode; }

    public BlacklistResultCode getResultCodeEnum() {
        return BlacklistResultCode.fromCode(resultCode);
    }

    public boolean isSuccessful() {
        return resultCode == BlacklistResultCode.SUCCESS.getCode();
    }
}
