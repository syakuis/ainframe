package org.syaku.exemple;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 28.
 */

import org.ainframe.web.config.EnableWebView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.syaku")
@EnableJpaRepositories("org.syaku")
@EnableWebView
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
