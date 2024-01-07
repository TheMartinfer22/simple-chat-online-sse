package dev.nanosync.chatonlineintegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class ChatConfig {

    @Bean
    public Sinks.Many<String> chatSink() {
        return Sinks.many().multicast().onBackpressureBuffer();
    }
}