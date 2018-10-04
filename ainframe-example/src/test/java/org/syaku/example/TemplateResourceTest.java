package org.syaku.example;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 1.
 */

import lombok.extern.slf4j.Slf4j;
import org.ainframe.webmvc.config.WebProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TemplateResourceTest {
    @Autowired
    private WebProperties webProperties;

    @Test
    public void test() {
        assertNotNull(webProperties.getTemplateLoaderPaths());

        String[] templateLoaderPaths = webProperties.getTemplateLoaderPaths();

        for (String templateLoaderPath : templateLoaderPaths) {
          String templatePath = templateLoaderPath + "modules/demo/skins/tpl/demo.list.ftl";
          assertTrue(new PathMatchingResourcePatternResolver().getResource(templatePath).exists());
          assertTrue(new PathMatchingResourcePatternResolver().getResource(
              templateLoaderPath + "index.ftl").exists());
        }
    }

    @Test
    public void freeMarkerTemplateLoaderTest() throws Exception {
      PathMatchingResourcePatternResolver matching = new PathMatchingResourcePatternResolver();
      Resource[] resources = matching.getResources("classpath*:META-INF/templates/");
      for (Resource resource : resources) {
        log.debug("{}", resource.getURI().getPath());
        log.debug("{}", resource.getURL().getFile());
      }
    }
}
