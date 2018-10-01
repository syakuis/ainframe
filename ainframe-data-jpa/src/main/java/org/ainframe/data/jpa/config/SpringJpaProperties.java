package org.ainframe.data.jpa.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 1.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.jpa")
@Data
public class SpringJpaProperties {
    private Properties properties;
}
