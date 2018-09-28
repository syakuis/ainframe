package org.ainframe.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.ainframe.core.util.Label;
import org.ainframe.data.jpa.config.JpaProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 7.
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name="ainframe.data.jpa.enable", havingValue="true")
@EnableConfigurationProperties(JpaProperties.class)
@EntityScan("org.ainframe")
@EnableJpaRepositories("org.ainframe")
public class JpaDataAutoConfiguration {
    public JpaDataAutoConfiguration() {
        if (log.isDebugEnabled()) {
            new Label().title("DATA-JPA AUTO CONFIGURATION").debug();
        }
    }
}

