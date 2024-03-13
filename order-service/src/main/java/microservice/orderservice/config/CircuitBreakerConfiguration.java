/*
package microservice.orderservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public CircuitBreaker inventoryCircuitBreaker() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .failureRateThreshold(50)
                .waitDurationInOpenState(java.time.Duration.ofSeconds(5))
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();

        return CircuitBreaker.of("inventory", circuitBreakerConfig);
    }

    @Bean
    public TimeLimiter timeLimiterRegistry() {
        TimeLimiterConfig inventoryTimeLimiterConfig = TimeLimiterConfig.custom()
                .timeoutDuration(java.time.Duration.ofSeconds(3))
                .build();
        return TimeLimiter.of("inventory", inventoryTimeLimiterConfig);
    }


    @Bean
    public Retry inventoryRetry() {
        RetryConfig inventoryRetryConfig= RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(java.time.Duration.ofSeconds(5))
                .build();

        return Retry.of("inventory", inventoryRetryConfig);
    }


}
*/
