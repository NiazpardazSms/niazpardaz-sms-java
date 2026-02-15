package com.niazpardaz.sms.models;

/**
 * نتیجه دریافت اعتبار
 */
public class CreditResult {
    private double credit;
    private int resultCode;

    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }

    public int getResultCode() { return resultCode; }
    public void setResultCode(int resultCode) { this.resultCode = resultCode; }

    public CreditResultCode getResultCodeEnum() {
        return CreditResultCode.fromCode(resultCode);
    }

    public boolean isSuccessful() {
        return resultCode == CreditResultCode.SUCCESS.getCode();
    }

    @Override
    public String toString() {
        return "CreditResult{credit=" + credit + ", resultCode=" + resultCode + "}";
    }
}
