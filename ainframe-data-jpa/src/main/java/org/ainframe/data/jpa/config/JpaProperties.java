package org.ainframe.data.jpa.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 28.
 */
@Configuration
@PropertySource("classpath:org/ainframe/data/jpa/config/jpa.properties")
@ConfigurationProperties(prefix = "ainframe.data.jpa")
@Validated
@Data
public class JpaProperties {
    @NotNull
    private boolean enable;
}
