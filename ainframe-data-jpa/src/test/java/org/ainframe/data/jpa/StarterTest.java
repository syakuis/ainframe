package org.ainframe.data.jpa;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 28.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StarterTest {
    @Value("${spring.data.jpa.repositories.enabled}")
    private boolean enable;

    @Test
    public void test() {
        assertTrue(enable);
    }
}
