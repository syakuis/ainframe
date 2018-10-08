package org.ainframe.webmvc.view;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 1.
 */

import org.ainframe.webmvc.support.freemarker.TemplateFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexTemplateTest {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Test
    public void test() {
      TemplateFinder templateFinder = new TemplateFinder(freeMarkerConfigurer);
      assertTrue(templateFinder.exists("index.ftl"));
    }
}
