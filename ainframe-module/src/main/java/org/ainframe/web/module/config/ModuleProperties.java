package org.ainframe.web.module.config;

import lombok.Data;
import org.ainframe.core.module.ModuleProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 21.
 */
@Configuration
@PropertySource("classpath:ainframe/config/module.properties")
@ConfigurationProperties(prefix = "ainframe.web.module")
@Validated
@Data
public class ModuleProperties implements ModuleProperty {
    @NotNull
    private String moduleName;
    @NotNull
    private String moduleIdx;
    @NotNull
    private boolean single;
}