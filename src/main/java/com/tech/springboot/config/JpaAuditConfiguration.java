package com.tech.springboot.config;

import com.tech.springboot.model.RequestInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "dateTimeProvider")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@Slf4j
public class JpaAuditConfiguration {
    @Component("auditorAware")
    @RequiredArgsConstructor
    class AuditorAwareImpl implements AuditorAware<String> {
        private final RequestInfo requestInfo;

        @Override
        public Optional<String> getCurrentAuditor() {
            String username = null;
            try {
                if (this.requestInfo != null) {
                    username = this.requestInfo.getUsername();
                }
                return Optional.of(username);
            } catch (Exception e) {
                log.error("Can not get username from request: {}", e.getMessage());
                return Optional.ofNullable(System.getProperties()
                        .get("user.name").toString());
            }
        }
    }

    @Component("dateTimeProvider")
    class DefaultDateTimeProvider implements DateTimeProvider {

        @Override
        public Optional<TemporalAccessor> getNow() {
            return Optional.of(OffsetDateTime.now());
        }
    }
}
