package com.niazpardaz.sms.models;

/**
 * اطلاعات پیامک
 */
public class MessageInfo {
    private long messageId;
    private int userId;
    private double tariff;
    private String content;
    private String actionDateTime;
    private int messageType;
    private String sender;
    private String receiver;
    private boolean flash;
    private int pages;
    private int lang;
    private int status;
    private int sendStatus;
    private int sendMethod;
    private double cost;
    private String title;
    private int count;
    private int sent;
    private String desc;
    private int notSent;
    private boolean moneyIsRefunded;
    private boolean isRead;

    // Getters and Setters
    public long getMessageId() { return messageId; }
    public void setMessageId(long messageId) { this.messageId = messageId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getTariff() { return tariff; }
    public void setTariff(double tariff) { this.tariff = tariff; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getActionDateTime() { return actionDateTime; }
    public void setActionDateTime(String actionDateTime) { this.actionDateTime = actionDateTime; }

    public int getMessageType() { return messageType; }
    public void setMessageType(int messageType) { this.messageType = messageType; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }

    public boolean isFlash() { return flash; }
    public void setFlash(boolean flash) { this.flash = flash; }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    public int getLang() { return lang; }
    public void setLang(int lang) { this.lang = lang; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public int getSendStatus() { return sendStatus; }
    public void setSendStatus(int sendStatus) { this.sendStatus = sendStatus; }

    public int getSendMethod() { return sendMethod; }
    public void setSendMethod(int sendMethod) { this.sendMethod = sendMethod; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public int getSent() { return sent; }
    public void setSent(int sent) { this.sent = sent; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public int getNotSent() { return notSent; }
    public void setNotSent(int notSent) { this.notSent = notSent; }

    public boolean isMoneyIsRefunded() { return moneyIsRefunded; }
    public void setMoneyIsRefunded(boolean moneyIsRefunded) { this.moneyIsRefunded = moneyIsRefunded; }

    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
}
