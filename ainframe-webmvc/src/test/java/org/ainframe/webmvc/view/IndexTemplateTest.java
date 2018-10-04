package org.ainframe.webmvc.view;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 1.
 */
import org.ainframe.webmvc.config.WebProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexTemplateTest {
    @Autowired
    private WebProperties webProperties;

    @Test
    public void test() {
      for (String templateLoaderPath: webProperties.getTemplateLoaderPaths()) {
        assertTrue(templateLoaderPath + ", not found", new PathMatchingResourcePatternResolver().getResource(
             templateLoaderPath + "index.ftl").exists());
      }
    }
}
