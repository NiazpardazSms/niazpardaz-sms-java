package com.niazpardaz.sms;

import com.niazpardaz.sms.client.NiazpardazSmsClient;
import com.niazpardaz.sms.client.NiazpardazSmsClientBuilder;
import com.niazpardaz.sms.exceptions.NiazpardazException;
import com.niazpardaz.sms.models.SendResultCode;
import com.niazpardaz.sms.models.DeliveryResultCode;
import com.niazpardaz.sms.models.CreditResultCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NiazpardazSmsClientTest {

    @Test
    void testEmptyApiKeyThrowsException() {
        assertThrows(NiazpardazException.class, () ->
                NiazpardazSmsClientBuilder.create("").build()
        );
    }

    @Test
    void testNullApiKeyThrowsException() {
        assertThrows(NiazpardazException.class, () ->
                NiazpardazSmsClientBuilder.create(null).build()
        );
    }

    @Test
    void testClientCanBeBuilt() {
        NiazpardazSmsClient client = NiazpardazSmsClientBuilder
                .create("test-key")
                .connectTimeout(5000)
                .readTimeout(15000)
                .build();
        assertNotNull(client);
    }

    @Test
    void testSendResultCodeFromCode() {
        assertEquals(SendResultCode.SEND_WAS_SUCCESSFUL, SendResultCode.fromCode(0));
        assertEquals(SendResultCode.NO_CREDIT, SendResultCode.fromCode(8));
        assertEquals(SendResultCode.INVALID_API_KEY, SendResultCode.fromCode(25));
        assertEquals(SendResultCode.UNKNOWN, SendResultCode.fromCode(999));
    }

    @Test
    void testDeliveryResultCodeFromCode() {
        assertEquals(DeliveryResultCode.SUCCESS, DeliveryResultCode.fromCode(0));
        assertEquals(DeliveryResultCode.INVALID_API_KEY, DeliveryResultCode.fromCode(-7));
        assertEquals(DeliveryResultCode.UNKNOWN, DeliveryResultCode.fromCode(-99));
    }

    @Test
    void testCreditResultCodeFromCode() {
        assertEquals(CreditResultCode.SUCCESS, CreditResultCode.fromCode(0));
        assertEquals(CreditResultCode.INVALID_API_KEY, CreditResultCode.fromCode(-7));
    }

    @Test
    void testBuilderWithCustomBaseUrl() {
        NiazpardazSmsClient client = NiazpardazSmsClientBuilder
                .create("test-key")
                .baseUrl("https://custom-api.example.com")
                .build();
        assertNotNull(client);
    }
}
