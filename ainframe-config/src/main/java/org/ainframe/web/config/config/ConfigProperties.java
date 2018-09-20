package org.ainframe.web.config.config;

import javax.validation.constraints.NotNull;

import org.ainframe.core.module.ModuleProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 30.
 */
@Configuration
@PropertySource("classpath:org/ainframe/web/config/config/config.properties")
@ConfigurationProperties(prefix = "ainframe.web.config")
@Validated
@Data
public class ConfigProperties implements ModuleProperties {
    @NotNull
    private String moduleName;
    @NotNull
    private String moduleIdx;
    @NotNull
    private boolean single;
    /**
     * {@link org.ainframe.context.ConfigContext} 에서 사용하는 캐시 이름
     */
    private String contextCacheName;
}
