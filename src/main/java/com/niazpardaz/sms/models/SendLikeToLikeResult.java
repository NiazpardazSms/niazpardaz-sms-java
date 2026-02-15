package com.niazpardaz.sms.models;

/**
 * نتیجه ارسال پیامک نظیر به نظیر
 */
public class SendLikeToLikeResult {
    private long smsId;
    private int resultCode;

    public long getSmsId() { return smsId; }
    public void setSmsId(long smsId) { this.smsId = smsId; }

    public int getResultCode() { return resultCode; }
    public void setResultCode(int resultCode) { this.resultCode = resultCode; }

    public SendResultCode getResultCodeEnum() {
        return SendResultCode.fromCode(resultCode);
    }

    public boolean isSuccessful() {
        return resultCode == SendResultCode.SEND_WAS_SUCCESSFUL.getCode();
    }

    @Override
    public String toString() {
        return "SendLikeToLikeResult{smsId=" + smsId +
                ", resultCode=" + resultCode +
                " (" + getResultCodeEnum().getDescription() + ")}";
    }
}
