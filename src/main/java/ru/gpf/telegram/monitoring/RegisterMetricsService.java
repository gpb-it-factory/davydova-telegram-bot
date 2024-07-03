package ru.gpf.telegram.monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class RegisterMetricsService {
    private final Counter registerMetricCounter;

    public RegisterMetricsService(MeterRegistry meterRegistry) {
        registerMetricCounter = Counter.builder("user_register")
                .description("number of registered users")
                .tags("business", "user")
                .register(meterRegistry);
    }

    public void incrementRegisterMetric() {
        registerMetricCounter.increment();
    }
}
