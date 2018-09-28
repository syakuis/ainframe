package org.ainframe.web.layout.service;

import org.ainframe.context.LayoutContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class LayoutContextTest {
    @Autowired
    private CacheLayoutContextService contextService;

    @Autowired
    private LayoutContext layoutContext;

    @Test
    public void test() {
        assertNotNull(contextService);
        assertNotNull(layoutContext);

        assertSame(layoutContext.getLayout("LAOUT000000000000005"),
            contextService.getLayout("LAOUT000000000000005"));

        assertSame(layoutContext.getLayout("LAOUT0000000000ADMIN"),
            contextService.getLayout("LAOUT0000000000ADMIN"));
    }
}
