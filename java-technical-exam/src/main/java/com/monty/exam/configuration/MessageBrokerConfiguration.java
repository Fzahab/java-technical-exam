package com.monty.exam.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBrokerConfiguration {

	 @Autowired
	    private ApplicationProperties appProperties;
    @Bean
    public Queue otpQueue() {
        return new Queue(appProperties.otpQueueName, true);
    }
}