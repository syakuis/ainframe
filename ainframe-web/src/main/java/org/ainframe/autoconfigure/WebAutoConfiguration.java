package org.ainframe.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.ainframe.web.banner.Banner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 7.
 */
@Slf4j
@Configuration
@ComponentScan("org.ainframe")
public class WebAutoConfiguration {
    @Bean
    public Banner banner() {
        return new Banner();
    }
}
