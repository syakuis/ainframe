package org.ainframe.web.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ModuleResolverTest {

    @Autowired
    private ModuleViewResolver mvr;

    /**
     * ModuleViewFactory Test
     */
    @Test
    public void factory() {

    }

    /**
     * ModuleViewResolver#loader Test
     */
    @Test
    public void loader() {
        // module = moduleName
        ModuleView mv = mvr.loader(moduleId, module);

        assertEquals(mv.getModuleId(), moduleId);
        assertEquals(mv.getModule(), module);
    }

    /**
     * ModuleViewResolver#render Test
     */
    @Test
    public void render() {
        // module = moduleName
        ModuleViewRender mv = mvr.render(moduleId, module);

        assertEquals(mv.getModuleId(), moduleId);
        assertEquals(mv.getModule(), module);
    }
}
