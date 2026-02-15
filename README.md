# Niazpardaz SMS SDK for Java

Official Java SDK for Niazpardaz SMS API | کتابخانه رسمی جاوا برای API پیامکی نیازپرداز

[![Maven Central](https://img.shields.io/maven-central/v/com.niazpardaz/niazpardazSms.svg)](https://search.maven.org/artifact/com.niazpardaz/niazpardazSms)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Tests](https://github.com/NiazpardazSms/niazpardaz-sms-java/actions/workflows/tests.yml/badge.svg)](https://github.com/NiazpardazSms/niazpardaz-sms-java/actions)

## Features | امکانات

- **Zero HTTP dependency** — Uses `java.net.HttpURLConnection` (built-in JDK)
- **Java 11+** — Supports Java 11, 17, 21
- **Spring Boot** — Auto-Configuration (supports both 2.x and 3.x)
- **Framework-agnostic** — Works with Quarkus, Micronaut, Jakarta EE, Vert.x, Spark, Dropwizard, Play, ...
- **Builder pattern** — Clean, fluent API
- **Fully typed** — All result codes as Java enums

---

## نصب | Installation

### Maven
```xml
<dependency>
    <groupId>com.niazpardaz</groupId>
    <artifactId>niazpardazSms</artifactId>
    <version>1.0.2</version>
</dependency>
```

### Gradle
```groovy
implementation 'com.niazpardaz:niazpardazSms:1.0.2'
```

---

## شروع سریع | Quick Start

```java
import com.niazpardaz.sms.client.*;
import com.niazpardaz.sms.models.*;

NiazpardazSmsClient client = NiazpardazSmsClientBuilder
    .create("YOUR_API_KEY")
    .build();

// ارسال پیامک
SendBatchSmsResult result = client.send("10001234", "09123456789", "سلام از نیازپرداز!");

if (result.isSuccessful()) {
    System.out.println("BatchSmsId: " + result.getBatchSmsId());
} else {
    System.out.println("Error: " + result.getResultCodeEnum().getDescription());
}
```

---

## استفاده در فریم‌ورک‌ها | Framework Integration

### Spring Boot (2.x & 3.x)

فقط dependency رو اضافه کن و API Key رو در `application.properties` بذار:

```properties
niazpardaz.sms.api-key=YOUR_API_KEY
niazpardaz.sms.connect-timeout=10000
niazpardaz.sms.read-timeout=30000
```

بعد هر جا نیاز داشتی `@Autowired` کن:

```java
@RestController
public class SmsController {

    @Autowired
    private NiazpardazSmsClient smsClient;

    @PostMapping("/send")
    public String send(@RequestParam String to, @RequestParam String message) {
        SendBatchSmsResult result = smsClient.send("10001234", to, message);
        return result.isSuccessful() ? "Sent!" : "Failed: " + result.getResultCodeEnum();
    }
}
```

یا با Constructor Injection:

```java
@Service
public class NotificationService {

    private final NiazpardazSmsClient smsClient;

    public NotificationService(NiazpardazSmsClient smsClient) {
        this.smsClient = smsClient;
    }

    public void sendOtp(String phone, String code) {
        smsClient.sendVoiceOtp("10001234", phone, code);
    }
}
```

### Quarkus

```java
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SmsProducer {

    @ConfigProperty(name = "niazpardaz.sms.api-key")
    String apiKey;

    @Produces
    @ApplicationScoped
    public NiazpardazSmsClient smsClient() {
        return NiazpardazSmsClientBuilder.create(apiKey).build();
    }
}
```

```properties
# application.properties
niazpardaz.sms.api-key=YOUR_API_KEY
```

```java
@ApplicationScoped
public class SmsService {
    @Inject
    NiazpardazSmsClient smsClient;

    public void send(String to, String message) {
        smsClient.send("10001234", to, message);
    }
}
```

### Micronaut

```java
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

@Factory
public class SmsFactory {

    @Bean
    @Singleton
    public NiazpardazSmsClient smsClient(
            @Value("${niazpardaz.sms.api-key}") String apiKey) {
        return NiazpardazSmsClientBuilder.create(apiKey).build();
    }
}
```

```yaml
# application.yml
niazpardaz:
  sms:
    api-key: YOUR_API_KEY
```

### Jakarta EE / Java EE

```java
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class SmsProducer {

    @Produces
    @ApplicationScoped
    public NiazpardazSmsClient createClient() {
        String apiKey = System.getenv("NIAZPARDAZ_SMS_API_KEY");
        return NiazpardazSmsClientBuilder.create(apiKey).build();
    }
}
```

### Vert.x

```java
NiazpardazSmsClient client = NiazpardazSmsClientBuilder
    .create(config.getString("NIAZPARDAZ_API_KEY"))
    .build();

router.post("/sms/send").handler(ctx -> {
    String to = ctx.request().getParam("to");
    String message = ctx.request().getParam("message");

    vertx.executeBlocking(promise -> {
        SendBatchSmsResult result = client.send("10001234", to, message);
        promise.complete(result);
    }, res -> {
        if (res.succeeded()) {
            ctx.response().end("Sent: " + ((SendBatchSmsResult)res.result()).getBatchSmsId());
        }
    });
});
```

### Spark Java

```java
import static spark.Spark.*;

NiazpardazSmsClient client = NiazpardazSmsClientBuilder.create("YOUR_API_KEY").build();

post("/send", (req, res) -> {
    SendBatchSmsResult result = client.send("10001234", req.queryParams("to"), req.queryParams("message"));
    return result.isSuccessful() ? "OK" : "Error";
});
```

### Dropwizard

```java
public class SmsBundle implements ConfiguredBundle<AppConfig> {
    @Override
    public void run(AppConfig config, Environment env) {
        NiazpardazSmsClient client = NiazpardazSmsClientBuilder
            .create(config.getSmsApiKey())
            .build();

        env.jersey().register(new SmsResource(client));
    }
}
```

### Plain Java (بدون فریم‌ورک)

```java
public class Main {
    public static void main(String[] args) {
        NiazpardazSmsClient client = NiazpardazSmsClientBuilder
            .create("YOUR_API_KEY")
            .build();

        SendBatchSmsResult result = client.send("10001234", "09123456789", "سلام!");
        System.out.println(result);
    }
}
```

### Android

```groovy
// build.gradle (app)
implementation 'com.niazpardaz:niazpardazSms:1.0.2'
```

```java
// باید در Background Thread اجرا بشه
new Thread(() -> {
    NiazpardazSmsClient client = NiazpardazSmsClientBuilder
        .create("YOUR_API_KEY")
        .build();
    SendBatchSmsResult result = client.send("10001234", "09123456789", "سلام!");
    runOnUiThread(() -> Toast.makeText(this, "Sent: " + result.getBatchSmsId(), Toast.LENGTH_SHORT).show());
}).start();
```

---

## تمام متدها | API Reference

| متد | توضیحات |
|-----|---------|
| `send(from, to, message)` | ارسال پیامک تکی |
| `sendBulk(from, toList, message)` | ارسال گروهی |
| `sendSmsLikeToLike(from, toList, messageList)` | ارسال نظیر به نظیر |
| `sendVoiceOtp(from, to, otp)` | ارسال OTP صوتی |
| `getBatchDelivery(batchSmsId, page, size)` | گزارش تحویل گروهی |
| `getDeliveryLikeToLike(smsId)` | گزارش تحویل نظیر به نظیر |
| `getCredit()` | اعتبار باقیمانده |
| `getSenderNumbers()` | شماره‌های فرستنده |
| `getInboxCount(isRead)` | تعداد پیام‌های دریافتی |
| `getMessages(type, from, page, size)` | لیست پیامک‌ها |
| `getMessagesByDateRange(type, from, fromDate, toDate)` | پیامک‌ها بر اساس بازه |
| `extractTelecomBlacklistNumbers(numbers)` | استخراج شماره‌های لیست سیاه |
| `numberIsInTelecomBlacklist(number)` | بررسی لیست سیاه |
| `checkSmsContent(message)` | بررسی محتوای پیامک |

---

## مدیریت خطا | Error Handling

```java
import com.niazpardaz.sms.exceptions.*;

try {
    SendBatchSmsResult result = client.send("10001234", "09123456789", "تست");
} catch (NiazpardazApiException e) {
    // خطای منطقی API (اعتبار ناکافی، شماره نامعتبر و...)
    System.err.println("API Error: " + e.getMessage());
    System.err.println("Error Code: " + e.getErrorCode());
} catch (NiazpardazNetworkException e) {
    // خطای شبکه (timeout, DNS, ...)
    System.err.println("Network Error: " + e.getMessage());
} catch (NiazpardazException e) {
    // خطای عمومی
    System.err.println("Error: " + e.getMessage());
}
```

---

## نیازمندی‌ها | Requirements

- Java 11 یا بالاتر
- Gson (تنها وابستگی اجباری)

## مجوز | License

MIT License — [LICENSE](LICENSE)

## پشتیبانی | Support

- مستندات: https://niazpardaz-sms.com/webservice
- گزارش باگ: [GitHub Issues](https://github.com/NiazpardazSms/niazpardaz-sms-java/issues)
