package ru.gpf.telegram.monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountMetricService {
    private final Counter createAccountMetricCounter;

    public CreateAccountMetricService(MeterRegistry meterRegistry) {
        createAccountMetricCounter = Counter.builder("account_register")
                .description("number of registered accounts")
                .tags("business", "account")
                .register(meterRegistry);
    }

    public void incrementRegisterMetric() {
        createAccountMetricCounter.increment();
    }
}
