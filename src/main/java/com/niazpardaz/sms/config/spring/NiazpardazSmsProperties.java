package com.niazpardaz.sms.config.spring;

/**
 * تنظیمات Spring Boot
 * <p>
 * در application.properties یا application.yml:
 * <pre>
 * niazpardaz.sms.api-key=YOUR_API_KEY
 * niazpardaz.sms.connect-timeout=10000
 * niazpardaz.sms.read-timeout=30000
 * </pre>
 */
public class NiazpardazSmsProperties {

    private String apiKey;
    private String baseUrl = "https://login.niazpardaz.ir/api/v2/RestWebApi";
    private int connectTimeout = 10_000;
    private int readTimeout = 30_000;

    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

    public int getConnectTimeout() { return connectTimeout; }
    public void setConnectTimeout(int connectTimeout) { this.connectTimeout = connectTimeout; }

    public int getReadTimeout() { return readTimeout; }
    public void setReadTimeout(int readTimeout) { this.readTimeout = readTimeout; }
}
