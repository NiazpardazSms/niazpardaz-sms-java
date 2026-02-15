package com.niazpardaz.sms.models;

/**
 * کدهای نتیجه اعتبار
 */
public enum CreditResultCode {
    SUCCESS(0, "موفق"),
    INVALID_CREDENTIALS(-1, "نام کاربری و رمز عبور صحیح نمی باشد"),
    USER_DISABLED(-2, "کاربر غیرفعال"),
    IP_BLOCKED(-6, "آی پی بلاک شده"),
    INVALID_API_KEY(-7, "ApiKey نامعتبر"),
    UNKNOWN(-99, "نامشخص");

    private final int code;
    private final String description;

    CreditResultCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }
    public String getDescription() { return description; }

    public static CreditResultCode fromCode(int code) {
        for (CreditResultCode v : values()) {
            if (v.code == code) return v;
        }
        return UNKNOWN;
    }
}
