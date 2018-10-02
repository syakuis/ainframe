package org.ainframe.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.ainframe.core.util.Label;
import org.ainframe.data.jpa.config.JpaProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * todo ConditionalOnProperty 검사에서 PropertySource 값을 읽지 못함. application.properties 설정해줘야 한다. 좀 더 깔끔하게 처리할 수 있게 개선한다. syakuis/ainframe#20
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 7.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(JpaProperties.class)
//@ConditionalOnProperty(name="ainframe.data.jpa.enable", havingValue="true")
@EntityScan("org.ainframe")
@EnableJpaRepositories("org.ainframe")
public class JpaDataAutoConfiguration {
    public JpaDataAutoConfiguration() {
        if (log.isDebugEnabled()) {
            new Label().title("DATA-JPA AUTO CONFIGURATION").debug();
        }
    }
}

