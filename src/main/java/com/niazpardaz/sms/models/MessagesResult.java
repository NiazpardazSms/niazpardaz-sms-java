package com.niazpardaz.sms.models;

import java.util.Collections;
import java.util.List;

/**
 * نتیجه دریافت پیامک‌ها
 */
public class MessagesResult {
    private List<MessageInfo> messages;
    private int resultCode;

    public List<MessageInfo> getMessages() {
        return messages != null ? messages : Collections.emptyList();
    }
    public void setMessages(List<MessageInfo> messages) { this.messages = messages; }

    public int getResultCode() { return resultCode; }
    public void setResultCode(int resultCode) { this.resultCode = resultCode; }
}
