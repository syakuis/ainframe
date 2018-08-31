package org.ainframe.web.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.ainframe.web.config.config.ConfigProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@ActiveProfiles("test")
public class PropertiesTest {
    @Autowired
    private ConfigProperties configProperties;

    @Value("${ainframe.version}")
    private String version;

    @Value("${ainframe.test}")
    private int test;

    @Test
    public void test() {
        assertNotNull(version);
        assertEquals(test, 2);
        assertEquals(configProperties.getModuleIdx(), "MODUL000000000CONFIG");
    }
}
