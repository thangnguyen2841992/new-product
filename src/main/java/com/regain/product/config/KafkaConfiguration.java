package com.regain.product.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    NewTopic checkEmailNotificationTopic() {
        return new NewTopic("send-email-notification",2,(short) 1);
    }
}
