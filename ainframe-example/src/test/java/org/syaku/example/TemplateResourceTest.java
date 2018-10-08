package org.syaku.example;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 1.
 */

import lombok.extern.slf4j.Slf4j;
import org.ainframe.webmvc.support.freemarker.TemplateFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TemplateResourceTest {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Test
    public void test() {
        TemplateFinder templateFinder = new TemplateFinder(freeMarkerConfigurer);

        assertFalse(templateFinder.exists("modules/demo/skins/tpl/demo.list2.ftl"));
        assertTrue(templateFinder.exists("modules/demo/skins/tpl/demo.list.ftl"));
        assertTrue(templateFinder.exists("index.ftl"));
    }
}
