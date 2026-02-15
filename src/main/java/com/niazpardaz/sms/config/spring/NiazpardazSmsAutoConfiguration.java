package com.niazpardaz.sms.config.spring;

import com.niazpardaz.sms.client.NiazpardazSmsClient;
import com.niazpardaz.sms.client.NiazpardazSmsClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto-Configuration
 * <p>
 * به صورت خودکار {@link NiazpardazSmsClient} را به عنوان Bean ثبت می‌کند.
 * <p>
 * فقط کافیه در application.properties بنویسید:
 * <pre>
 * niazpardaz.sms.api-key=YOUR_API_KEY
 * </pre>
 */
@Configuration
@ConditionalOnClass(NiazpardazSmsClient.class)
@ConditionalOnProperty(prefix = "niazpardaz.sms", name = "api-key")
public class NiazpardazSmsAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "niazpardaz.sms")
    @ConditionalOnMissingBean(NiazpardazSmsProperties.class)
    public NiazpardazSmsProperties niazpardazSmsProperties() {
        return new NiazpardazSmsProperties();
    }

    @Bean
    @ConditionalOnMissingBean(NiazpardazSmsClient.class)
    public NiazpardazSmsClient niazpardazSmsClient(NiazpardazSmsProperties props) {
        return NiazpardazSmsClientBuilder.create(props.getApiKey())
                .baseUrl(props.getBaseUrl())
                .connectTimeout(props.getConnectTimeout())
                .readTimeout(props.getReadTimeout())
                .build();
    }
}
