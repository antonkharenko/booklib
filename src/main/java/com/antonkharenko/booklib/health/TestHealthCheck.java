package com.antonkharenko.booklib.health;

import com.codahale.metrics.health.HealthCheck;
import org.springframework.stereotype.Component;

@Component
public class TestHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
