package org.ainframe.web.menu.config;

import javax.validation.constraints.NotNull;

import org.ainframe.core.module.ModuleProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
@Configuration
@PropertySource("classpath:/org/ainframe/web/menu/config/application.properties")
@ConfigurationProperties(prefix = "ainframe.web.menu")
@Validated
@Data
public class MenuProperties implements ModuleProperties {
    @NotNull
    private String moduleName;
    @NotNull
    private String moduleIdx;
    @NotNull
    private boolean single;
    @NotNull
    private String adminMenuIdx;
    @NotNull
    private String defaultUrlPattern;
    @NotNull
    private String rootMenuId;

}
