package com.niazpardaz.sms.models;

/**
 * نتیجه تعداد پیام‌های دریافتی
 */
public class InboxCountResult {
    private int inboxCount;
    private int resultCode;

    public int getInboxCount() { return inboxCount; }
    public void setInboxCount(int inboxCount) { this.inboxCount = inboxCount; }

    public int getResultCode() { return resultCode; }
    public void setResultCode(int resultCode) { this.resultCode = resultCode; }
}
