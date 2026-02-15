package com.niazpardaz.sms.client;

/**
 * بیلدر برای ساخت کلاینت پیامکی نیازپرداز
 * <p>
 * نمونه استفاده:
 * <pre>{@code
 * NiazpardazSmsClient client = NiazpardazSmsClientBuilder
 *     .create("YOUR_API_KEY")
 *     .connectTimeout(10_000)
 *     .readTimeout(30_000)
 *     .build();
 * }</pre>
 */
public class NiazpardazSmsClientBuilder {

    private static final String DEFAULT_BASE_URL = "https://login.niazpardaz.ir/api/v2/RestWebApi";
    private static final int DEFAULT_CONNECT_TIMEOUT = 10_000; // 10 seconds
    private static final int DEFAULT_READ_TIMEOUT = 30_000;    // 30 seconds

    private final String apiKey;
    private String baseUrl = DEFAULT_BASE_URL;
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private int readTimeout = DEFAULT_READ_TIMEOUT;

    private NiazpardazSmsClientBuilder(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * شروع ساخت کلاینت با API Key
     *
     * @param apiKey کلید API از پنل نیازپرداز
     * @return builder instance
     */
    public static NiazpardazSmsClientBuilder create(String apiKey) {
        return new NiazpardazSmsClientBuilder(apiKey);
    }

    /**
     * تغییر آدرس پایه API (مفید برای تست)
     */
    public NiazpardazSmsClientBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * تایم‌اوت برقراری ارتباط (بر حسب میلی‌ثانیه)
     */
    public NiazpardazSmsClientBuilder connectTimeout(int connectTimeoutMs) {
        this.connectTimeout = connectTimeoutMs;
        return this;
    }

    /**
     * تایم‌اوت خواندن پاسخ (بر حسب میلی‌ثانیه)
     */
    public NiazpardazSmsClientBuilder readTimeout(int readTimeoutMs) {
        this.readTimeout = readTimeoutMs;
        return this;
    }

    /**
     * ساخت کلاینت
     */
    public NiazpardazSmsClient build() {
        return new DefaultNiazpardazSmsClient(apiKey, baseUrl, connectTimeout, readTimeout);
    }
}
