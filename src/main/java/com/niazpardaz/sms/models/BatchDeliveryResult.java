package com.niazpardaz.sms.models;

import java.util.Collections;
import java.util.List;

/**
 * نتیجه گزارش تحویل
 */
public class BatchDeliveryResult {
    private int resultCode;
    private List<String> numbers;
    private List<Integer> deliveryStatus;

    public int getResultCode() { return resultCode; }
    public void setResultCode(int resultCode) { this.resultCode = resultCode; }

    public List<String> getNumbers() {
        return numbers != null ? numbers : Collections.emptyList();
    }
    public void setNumbers(List<String> numbers) { this.numbers = numbers; }

    public List<Integer> getDeliveryStatus() {
        return deliveryStatus != null ? deliveryStatus : Collections.emptyList();
    }
    public void setDeliveryStatus(List<Integer> deliveryStatus) { this.deliveryStatus = deliveryStatus; }

    public DeliveryResultCode getResultCodeEnum() {
        return DeliveryResultCode.fromCode(resultCode);
    }

    public boolean isSuccessful() {
        return resultCode == DeliveryResultCode.SUCCESS.getCode();
    }
}
