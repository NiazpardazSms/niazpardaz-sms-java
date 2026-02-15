package com.niazpardaz.sms.models;

/**
 * نتیجه ارسال پیامک گروهی
 */
public class SendBatchSmsResult {
    private long batchSmsId;
    private int resultCode;

    public long getBatchSmsId() { return batchSmsId; }
    public void setBatchSmsId(long batchSmsId) { this.batchSmsId = batchSmsId; }

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
        return "SendBatchSmsResult{batchSmsId=" + batchSmsId +
                ", resultCode=" + resultCode +
                " (" + getResultCodeEnum().getDescription() + ")}";
    }
}
