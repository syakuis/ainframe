package org.ainframe.context.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ModuleContextTest {
    @Autowired
    private ModuleContext moduleContext;

    @Autowired
    private ModuleContextService moduleContextService;

    @Test
    public void test() {
        assertEquals(
            moduleContext.getModule("module"),
            moduleContextService.getModuleStore().getModule("module")
        );

        assertEquals(
            moduleContext.getModuleNameByModuleId("module"),
            moduleContextService.getModuleStore().getModuleNameByModuleId("module")
        );

        assertEquals(
            moduleContext.getModules(),
            moduleContextService.getModuleStore().values()
        );
    }
}
