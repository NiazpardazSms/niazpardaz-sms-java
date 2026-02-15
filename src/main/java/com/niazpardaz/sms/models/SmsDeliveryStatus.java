package com.niazpardaz.sms.models;

/**
 * وضعیت تحویل پیامک
 */
public enum SmsDeliveryStatus {
    SENT_TO_TELECOM(0, "ارسال شده به مخابرات"),
    DELIVERED(1, "رسیده به گوشی"),
    NOT_DELIVERED(2, "نرسیده به گوشی"),
    SMS_FAILED(3, "خطای مخابراتی"),
    UNKNOWN_ERROR(4, "خطای نامشخص"),
    RECEIVED_BY_TELECOM(5, "رسیده به مخابرات"),
    NOT_RECEIVED_BY_TELECOM(6, "نرسیده به مخابرات"),
    BLACKLISTED(7, "مسدود شده توسط مقصد"),
    UNKNOWN(8, "نامشخص"),
    REJECTED_BY_TELECOM(9, "مخابرات پیام را مردود اعلام کرد"),
    CANCELED(10, "کنسل شده توسط اپراتور"),
    NOT_SENT(11, "ارسال نشده"),
    NO_TELEGRAM(12, "تلگرام ندارد"),
    IN_QUEUE(13, "در صف ارسال"),
    NONE(-10, "نامشخص");

    private final int code;
    private final String description;

    SmsDeliveryStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }
    public String getDescription() { return description; }

    public static SmsDeliveryStatus fromCode(int code) {
        for (SmsDeliveryStatus v : values()) {
            if (v.code == code) return v;
        }
        return NONE;
    }
}
