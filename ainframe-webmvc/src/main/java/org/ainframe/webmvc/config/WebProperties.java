package org.ainframe.webmvc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 27.
 */
@Configuration
@PropertySource("classpath:ainframe/web.properties")
@ConfigurationProperties(prefix = "ainframe.web.view")
@Validated
@Data
public class WebProperties {
    @NotNull
    private String indexTemplate;
    @NotNull
    private String adminLayoutIdx;
    @NotNull
    private String adminSkin;
    @NotNull
    private String skin;

    @Value("${spring.freemarker.template-loader-path}")
    private String[] templateLoaderPaths;
}
