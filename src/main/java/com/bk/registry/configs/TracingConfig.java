package com.bk.registry.configs;

import brave.Tracing;
import brave.propagation.StrictCurrentTraceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

    @Bean
    public Tracing tracing() {
        return Tracing.newBuilder()
                .traceId128Bit(false)  // traceId with 64 bits
                .currentTraceContext(StrictCurrentTraceContext.create())
                .build();
    }
}
