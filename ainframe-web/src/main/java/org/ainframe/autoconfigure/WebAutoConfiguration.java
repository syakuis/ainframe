package org.ainframe.autoconfigure;

import org.ainframe.core.util.Label;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 7.
 */
@Slf4j
@Configuration
@ComponentScan("org.ainframe")
public class WebAutoConfiguration {
    public WebAutoConfiguration() {
        if (log.isDebugEnabled()) {
            new Label().title("CONTEXT AUTO CONFIGURATION").debug();
        }
    }
}
