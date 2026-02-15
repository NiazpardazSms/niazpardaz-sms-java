package com.niazpardaz.sms.models;

/**
 * کدهای نتیجه گزارش تحویل
 */
public enum DeliveryResultCode {
    SUCCESS(0, "موفق"),
    SERVICE_CONNECTION_ERROR(-1, "خطا در ارتباط با سرویس دهنده"),
    INVALID_BATCH_SMS_ID(-2, "پیام با این کد وجود ندارد"),
    REPORT_EXPIRED(-3, "مهلت یک هفته ای گزارش پایان یافته"),
    MESSAGE_IN_QUEUE(-4, "پیام در صف ارسال مخابرات است"),
    TOO_EARLY(-5, "حداقل یک دقیقه بعد از ارسال اقدام نمایید"),
    IP_BLOCKED(-6, "آی پی بلاک شده"),
    INVALID_API_KEY(-7, "ApiKey نامعتبر"),
    UNKNOWN(-99, "نامشخص");

    private final int code;
    private final String description;

    DeliveryResultCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }
    public String getDescription() { return description; }

    public static DeliveryResultCode fromCode(int code) {
        for (DeliveryResultCode v : values()) {
            if (v.code == code) return v;
        }
        return UNKNOWN;
    }
}
