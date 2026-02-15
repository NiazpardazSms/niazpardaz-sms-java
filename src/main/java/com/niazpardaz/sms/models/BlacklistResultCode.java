package com.niazpardaz.sms.models;

/**
 * کدهای نتیجه لیست سیاه
 */
public enum BlacklistResultCode {
    SUCCESS(0, "موفق"),
    INVALID_CREDENTIALS(-1, "نام کاربری و رمز عبور صحیح نمی باشد"),
    USER_DISABLED(-2, "کاربر غیرفعال"),
    EMPTY_NUMBERS_ARRAY(-3, "آرایه شماره ها خالی"),
    MAX_NUMBERS_EXCEEDED(-4, "حداکثر 1000 شماره"),
    IP_BLOCKED(-6, "آی پی بلاک شده"),
    INVALID_API_KEY(-7, "ApiKey نامعتبر"),
    UNKNOWN(-99, "نامشخص");

    private final int code;
    private final String description;

    BlacklistResultCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }
    public String getDescription() { return description; }

    public static BlacklistResultCode fromCode(int code) {
        for (BlacklistResultCode v : values()) {
            if (v.code == code) return v;
        }
        return UNKNOWN;
    }
}
