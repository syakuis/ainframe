package org.ainframe.web.module.repository;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.transaction.Transactional;

import org.ainframe.web.module.domain.ModuleEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@Transactional
@ActiveProfiles("real")
public class RealModuleRepositoryTest {

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    public void test() {
        ModuleEntity aModuleEntity = moduleRepository.findOneByModuleId("board");
        assertThat(aModuleEntity.getOnlyUseTheme().isValue(), anyOf(is(true),is(false)));
    }

}
