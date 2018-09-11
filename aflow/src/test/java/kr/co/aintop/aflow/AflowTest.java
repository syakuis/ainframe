package kr.co.aintop.aflow;

import org.ainframe.web.module.context.ModuleContext;
import org.ainframe.web.module.model.Module;
import org.ainframe.web.module.repository.ModuleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AflowTest {
    @Autowired
    private ModuleContext moduleContext;

    @Autowired
    private Environment environment;

    @Test
    public void test() {
        Module module = moduleContext.getModule("board");
    }
}