package org.ainframe.web.config.config;

import lombok.Data;
import org.ainframe.core.module.ModuleProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 30.
 */
@Configuration
@PropertySource("classpath:ainframe/config/config.properties")
@ConfigurationProperties(prefix = "ainframe.web.config")
@Validated
@Data
public class ConfigProperties implements ModuleProperty {
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
