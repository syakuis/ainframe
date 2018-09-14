package org.ainframe.web.config.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigPropertiesTest {
    @Autowired
    private ConfigProperties configProperties;

    @Test
    public void test() {
        assertEquals(configProperties.getModuleName(), "config");
    }
}
