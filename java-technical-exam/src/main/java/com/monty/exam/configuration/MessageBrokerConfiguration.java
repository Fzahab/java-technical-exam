package com.monty.exam.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBrokerConfiguration {

    public static final String OTP_QUEUE = "otpQueue";

    @Bean
    public Queue otpQueue() {
        return new Queue(OTP_QUEUE, true); // durable = true
    }
}