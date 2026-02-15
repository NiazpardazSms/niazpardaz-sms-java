package com.niazpardaz.sms.models;

import java.util.Collections;
import java.util.List;

/**
 * نتیجه دریافت شماره‌های فرستنده
 */
public class SenderNumbersResult {
    private List<String> senders;
    private int resultCode;

    public List<String> getSenders() {
        return senders != null ? senders : Collections.emptyList();
    }
    public void setSenders(List<String> senders) { this.senders = senders; }

    public int getResultCode() { return resultCode; }
    public void setResultCode(int resultCode) { this.resultCode = resultCode; }
}
