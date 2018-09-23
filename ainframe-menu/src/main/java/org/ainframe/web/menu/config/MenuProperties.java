package org.ainframe.web.menu.config;

import lombok.Data;
import org.ainframe.core.module.ModuleProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
@Configuration
@PropertySource("classpath:org/ainframe/web/menu/config/menu.properties")
@ConfigurationProperties(prefix = "ainframe.web.menu")
@Validated
@Data
public class MenuProperties implements ModuleProperty {
    @NotNull
    private String moduleName;
    @NotNull
    private String moduleIdx;
    @NotNull
    private boolean single;
    @NotNull
    private String contextCacheName;
    @NotNull
    private String adminMenuIdx;
    @NotNull
    private String defaultUrlPattern;
    @NotNull
    private String rootMenuId;

}
