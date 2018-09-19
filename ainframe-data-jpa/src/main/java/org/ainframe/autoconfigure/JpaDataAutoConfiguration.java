package org.ainframe.autoconfigure;

import org.ainframe.core.util.Label;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 7.
 */
@Slf4j
@Configuration
@EntityScan("org.ainframe")
@EnableJpaRepositories("org.ainframe")
public class JpaDataAutoConfiguration {
    public JpaDataAutoConfiguration() {
        if (log.isDebugEnabled()) {
            new Label().title("DATA-JPA AUTO CONFIGURATION").debug();
        }
    }
}

