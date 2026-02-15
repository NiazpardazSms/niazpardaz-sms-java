package com.niazpardaz.sms.client;

import com.niazpardaz.sms.models.*;
import java.util.List;

/**
 * اینترفیس کلاینت پیامکی نیازپرداز
 * <p>
 * از این اینترفیس برای Dependency Injection و Mocking استفاده کنید.
 */
public interface NiazpardazSmsClient {

    SendBatchSmsResult send(String fromNumber, String toNumber, String message);
    SendBatchSmsResult send(String fromNumber, String toNumber, String message, boolean isFlash, Integer sendDelay);

    SendBatchSmsResult sendBulk(String fromNumber, List<String> toNumbers, String message);
    SendBatchSmsResult sendBulk(String fromNumber, List<String> toNumbers, String message, boolean isFlash, Integer sendDelay);

    SendLikeToLikeResult sendSmsLikeToLike(String fromNumber, List<String> toNumbers, List<String> messages);
    SendLikeToLikeResult sendSmsLikeToLike(String fromNumber, List<String> toNumbers, List<String> messages, boolean isFlash);

    SendBatchSmsResult sendVoiceOtp(String fromNumber, String toNumber, String otp);
    SendBatchSmsResult sendVoiceOtp(String fromNumber, String toNumber, String otp, boolean isFlash, Integer sendDelay);

    BatchDeliveryResult getBatchDelivery(long batchSmsId, int pageIndex, int pageSize);
    BatchDeliveryResult getDeliveryLikeToLike(long smsId);

    CreditResult getCredit();
    SenderNumbersResult getSenderNumbers();
    InboxCountResult getInboxCount(boolean isRead);

    MessagesResult getMessages(int messageType, String fromNumbers, int pageIndex, int pageSize);
    MessagesResult getMessagesByDateRange(int messageType, String fromNumbers, String fromDate, String toDate);

    BlacklistNumbersResult extractTelecomBlacklistNumbers(List<String> numbers);
    IsBlacklistResult numberIsInTelecomBlacklist(String number);
    CheckContentResult checkSmsContent(String message);
}
