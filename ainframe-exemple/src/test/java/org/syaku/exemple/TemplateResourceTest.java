package org.syaku.exemple;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 1.
 */

import lombok.extern.slf4j.Slf4j;
import org.ainframe.web.config.WebProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateResourceTest {
    @Autowired
    private WebProperties webProperties;

    @Test
    public void test() {
        assertNotNull(webProperties.getTemplateLoaderPath());

        String templatePath = webProperties.getTemplateLoaderPath() + "modules/demo/tpl/demo.list.ftl";
        assertTrue(new PathMatchingResourcePatternResolver().getResource(templatePath).exists());
        assertTrue(new PathMatchingResourcePatternResolver().getResource(
            webProperties.getTemplateLoaderPath() + "index.ftl").exists());
    }
}
