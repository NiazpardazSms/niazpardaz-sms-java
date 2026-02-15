package com.niazpardaz.sms.models;

/**
 * کدهای نتیجه ارسال پیامک
 */
public enum SendResultCode {
    SEND_WAS_SUCCESSFUL(0, "ارسال موفق"),
    INVALID_USERNAME_OR_PASSWORD(1, "نام کاربر یا رمز نامعتبر"),
    USER_BLOCKED(2, "کاربر مسدود"),
    INVALID_SENDER_NUMBER(3, "شماره فرستنده نامعتبر"),
    LIMITATION_IN_DAILY_SEND(4, "محدودیت روزانه"),
    LIMITATION_IN_RECEIVER_COUNT(5, "حداکثر 1000 گیرنده"),
    SENDER_LINE_IS_INACTIVE(6, "خط غیرفعال"),
    SMS_CONTENT_FILTERED(7, "کلمات فیلتر شده"),
    NO_CREDIT(8, "اعتبار ناکافی"),
    SYSTEM_BEING_UPDATED(9, "در حال بروزرسانی"),
    WEB_SERVICE_NOT_ACTIVE(10, "وب سرویس غیرفعال"),
    NOT_IMPLEMENTED(11, "پیاده سازی نشده"),
    LIKE_TO_LIKE_COUNT_MISMATCH(12, "تعداد پیام و شماره نابرابر"),
    LIMITATION_IN_MESSAGE_COUNT(13, "حداکثر 100 پیام"),
    USER_TARIFF_NOT_DETERMINED(14, "تعرفه تعریف نشده"),
    DUPLICATE_SEND_SMS(15, "ارسال تکراری"),
    INVALID_NUMBER_EMPTY_OR_BLACKLIST(16, "شماره نامعتبر یا بلاک لیست"),
    TEXT_NOT_FOUND(17, "متن خالی"),
    NOT_VALID_TEMPLATE_FOUND(18, "مغایرت با قالب"),
    USER_EXPIRED(19, "کاربر منقضی"),
    USER_IS_NOT_ACTIVE(20, "کاربر غیرفعال"),
    INVALID_PARAMETERS(21, "پارامتر نامعتبر"),
    IP_BLOCKED(22, "آی پی بلاک شده"),
    ENQUEUE_FAILED(23, "خطا در صف ارسال"),
    DUPLICATE_REQUEST_THRESHOLD(24, "درخواست تکراری"),
    INVALID_API_KEY(25, "ApiKey نامعتبر"),
    ERROR_CREATE_VOICE_FILE(26, "خطا در ساخت فایل صوتی"),
    UNKNOWN(-1, "نامشخص");

    private final int code;
    private final String description;

    SendResultCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }
    public String getDescription() { return description; }

    public static SendResultCode fromCode(int code) {
        for (SendResultCode v : values()) {
            if (v.code == code) return v;
        }
        return UNKNOWN;
    }
}
