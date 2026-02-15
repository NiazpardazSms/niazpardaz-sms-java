package com.niazpardaz.sms.client;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.niazpardaz.sms.exceptions.NiazpardazApiException;
import com.niazpardaz.sms.exceptions.NiazpardazException;
import com.niazpardaz.sms.exceptions.NiazpardazNetworkException;
import com.niazpardaz.sms.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * پیاده‌سازی اصلی کلاینت پیامکی نیازپرداز
 * <p>
 * از {@code java.net.HttpURLConnection} استفاده می‌کند (بدون وابستگی خارجی HTTP).
 * <p>
 * نمونه استفاده:
 * <pre>{@code
 * NiazpardazSmsClient client = NiazpardazSmsClientBuilder.create("YOUR_API_KEY").build();
 * SendBatchSmsResult result = client.send("10001234", "09123456789", "سلام!");
 * }</pre>
 */
public class DefaultNiazpardazSmsClient implements NiazpardazSmsClient {

    private static final Logger logger = LoggerFactory.getLogger(DefaultNiazpardazSmsClient.class);

    private final String apiKey;
    private final String baseUrl;
    private final int connectTimeout;
    private final int readTimeout;
    private final Gson gson;

    DefaultNiazpardazSmsClient(String apiKey, String baseUrl, int connectTimeout, int readTimeout) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new NiazpardazException("API Key نمی‌تواند خالی باشد");
        }
        this.apiKey = apiKey.trim();
        this.baseUrl = baseUrl;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.gson = new Gson();
    }

    // ─── Send Methods ────────────────────────────────────

    @Override
    public SendBatchSmsResult send(String fromNumber, String toNumber, String message) {
        return send(fromNumber, toNumber, message, false, null);
    }

    @Override
    public SendBatchSmsResult send(String fromNumber, String toNumber, String message,
                                   boolean isFlash, Integer sendDelay) {
        return sendBulk(fromNumber, Collections.singletonList(toNumber), message, isFlash, sendDelay);
    }

    @Override
    public SendBatchSmsResult sendBulk(String fromNumber, List<String> toNumbers, String message) {
        return sendBulk(fromNumber, toNumbers, message, false, null);
    }

    @Override
    public SendBatchSmsResult sendBulk(String fromNumber, List<String> toNumbers, String message,
                                       boolean isFlash, Integer sendDelay) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("fromNumber", fromNumber);
        payload.put("messageContent", message);
        payload.put("toNumbers", String.join(",", toNumbers));
        payload.put("isFlash", isFlash);
        payload.put("sendDelay", sendDelay);

        return post("/SendBatchSms", payload, SendBatchSmsResult.class);
    }

    @Override
    public SendLikeToLikeResult sendSmsLikeToLike(String fromNumber, List<String> toNumbers,
                                                    List<String> messages) {
        return sendSmsLikeToLike(fromNumber, toNumbers, messages, false);
    }

    @Override
    public SendLikeToLikeResult sendSmsLikeToLike(String fromNumber, List<String> toNumbers,
                                                    List<String> messages, boolean isFlash) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("fromNumber", fromNumber);
        payload.put("messageContents", String.join(",", messages));
        payload.put("toNumbers", String.join(",", toNumbers));
        payload.put("isFlash", isFlash);

        return post("/SendSmsLikeToLike", payload, SendLikeToLikeResult.class);
    }

    @Override
    public SendBatchSmsResult sendVoiceOtp(String fromNumber, String toNumber, String otp) {
        return sendVoiceOtp(fromNumber, toNumber, otp, false, null);
    }

    @Override
    public SendBatchSmsResult sendVoiceOtp(String fromNumber, String toNumber, String otp,
                                           boolean isFlash, Integer sendDelay) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("fromNumber", fromNumber);
        payload.put("messageContent", otp);
        payload.put("toNumbers", toNumber);
        payload.put("isFlash", isFlash);
        payload.put("sendDelay", sendDelay);

        return post("/SendVoiceOtp", payload, SendBatchSmsResult.class);
    }

    // ─── Delivery Reports ────────────────────────────────

    @Override
    public BatchDeliveryResult getBatchDelivery(long batchSmsId, int pageIndex, int pageSize) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("batchSmsId", batchSmsId);
        payload.put("index", pageIndex);
        payload.put("count", pageSize);

        return post("/GetBatchDelivery", payload, BatchDeliveryResult.class);
    }

    @Override
    public BatchDeliveryResult getDeliveryLikeToLike(long smsId) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("smsId", smsId);

        return post("/GetDeliveryLikeToLike", payload, BatchDeliveryResult.class);
    }

    // ─── Account Info ────────────────────────────────────

    @Override
    public CreditResult getCredit() {
        return post("/GetCredit", Collections.emptyMap(), CreditResult.class);
    }

    @Override
    public SenderNumbersResult getSenderNumbers() {
        return post("/GetSenderNumbers", Collections.emptyMap(), SenderNumbersResult.class);
    }

    @Override
    public InboxCountResult getInboxCount(boolean isRead) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("isRead", isRead);
        return post("/GetInboxCount", payload, InboxCountResult.class);
    }

    // ─── Messages ────────────────────────────────────────

    @Override
    public MessagesResult getMessages(int messageType, String fromNumbers, int pageIndex, int pageSize) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("messageType", messageType);
        payload.put("fromNumbers", fromNumbers);
        payload.put("index", pageIndex);
        payload.put("count", pageSize);

        return post("/GetMessages", payload, MessagesResult.class);
    }

    @Override
    public MessagesResult getMessagesByDateRange(int messageType, String fromNumbers,
                                                  String fromDate, String toDate) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("messageType", messageType);
        payload.put("fromNumbers", fromNumbers);
        payload.put("fromDate", fromDate);
        payload.put("toDate", toDate);

        return post("/GetMessagesByDateRange", payload, MessagesResult.class);
    }

    // ─── Blacklist & Content Check ───────────────────────

    @Override
    public BlacklistNumbersResult extractTelecomBlacklistNumbers(List<String> numbers) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("numbers", String.join(",", numbers));

        return post("/ExtractTelecomBlacklistNumbers", payload, BlacklistNumbersResult.class);
    }

    @Override
    public IsBlacklistResult numberIsInTelecomBlacklist(String number) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("number", number);

        return post("/NumberIsInTelecomBlacklist", payload, IsBlacklistResult.class);
    }

    @Override
    public CheckContentResult checkSmsContent(String message) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("message", message);

        return post("/CheckSmsContent", payload, CheckContentResult.class);
    }

    // ─── HTTP Layer ──────────────────────────────────────

    private <T> T post(String endpoint, Map<String, Object> payload, Class<T> resultType) {
        String url = baseUrl + endpoint;
        String jsonPayload = gson.toJson(payload);

        logger.debug("POST {} payload={}", endpoint, jsonPayload);

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.setRequestProperty("X-API-Key", apiKey);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");

            // Write body
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
            }

            // Check status code first
            int statusCode = conn.getResponseCode();
            if (statusCode != 200) {
                String errorBody = readStream(conn.getErrorStream());
                logger.error("HTTP {} from {}: {}", statusCode, endpoint, errorBody);
                throw new NiazpardazNetworkException(
                        "خطا در ارتباط با سرور: HTTP " + statusCode, statusCode);
            }

            // Read response
            String body = readStream(conn.getInputStream());
            logger.debug("Response from {}: {}", endpoint, body);

            return parseResponse(body, resultType);

        } catch (NiazpardazException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Network error calling {}: {}", endpoint, e.getMessage());
            throw new NiazpardazNetworkException("خطا در درخواست HTTP: " + e.getMessage(), e);
        }
    }

    private <T> T parseResponse(String body, Class<T> resultType) {
        JsonObject root = JsonParser.parseString(body).getAsJsonObject();

        boolean success = root.has("success") && root.get("success").getAsBoolean();
        if (!success) {
            String errorMessage = root.has("errorMessage")
                    ? root.get("errorMessage").getAsString() : "خطای نامشخص";
            int errorCode = 0;
            if (root.has("result")) {
                JsonElement resultEl = root.get("result");
                if (resultEl.isJsonObject() && resultEl.getAsJsonObject().has("resultCode")) {
                    errorCode = resultEl.getAsJsonObject().get("resultCode").getAsInt();
                }
            }
            throw new NiazpardazApiException(errorMessage, errorCode);
        }

        JsonElement resultElement = root.get("result");
        return gson.fromJson(resultElement, resultType);
    }

    private String readStream(InputStream is) throws IOException {
        if (is == null) return "";
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
    }
}
