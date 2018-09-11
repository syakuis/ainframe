package org.ainframe.web.autoconfigure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 7.
 */
@Configuration
@ComponentScan("org.ainframe")
@EntityScan("org.ainframe")
@EnableJpaRepositories("org.ainframe")
public class AinframeAutoConfiguration {
}
