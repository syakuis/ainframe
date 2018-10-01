package org.ainframe.data.jpa;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 1.
 */
import org.ainframe.data.jpa.config.JpaProperties;
import org.ainframe.data.jpa.config.SpringJpaProperties;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("hibernate")
public class PropertiesTest {
    @Autowired
    private SpringJpaProperties springJpaProperties;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private Environment environment;

    @Test
    public void test() {
        Properties properties = springJpaProperties.getProperties();
        assertEquals(properties.getProperty("hibernate.show_sql"), "false");

        Set<String> names = properties.stringPropertyNames();
        for (String name : names) {
            if (StringUtils.startsWith(name, "hibernate.")) {
                System.out.println(name);
            }
        }
    }
}
