package org.syaku.exemple;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 28.
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.syaku.exemple.app.demo.domain.Demo;
import org.syaku.exemple.app.demo.domain.DemoEntity;
import org.syaku.exemple.app.demo.repository.DemoRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class DemoRepositoryTest {
    @Autowired
    private DemoRepository demoRepository;

    @Test
    public void save() {
        DemoEntity demoEntity = DemoEntity.builder()
            .subject("안녕하세요.")
            .contents("데모를 테스트합니다.")
//            .creationDate(new Date())
            .build();
        demoRepository.save(demoEntity);

        Demo aDemoEntity = demoRepository.findOne(demoEntity.getDemoIdx());

        assertSame(demoEntity, aDemoEntity);
        assertEquals(demoEntity, aDemoEntity);

        demoEntity.setContents("데모 내용을 변경한다.");

        Demo aDemoEntity2 = demoRepository.findOne(demoEntity.getDemoIdx());
        assertEquals(aDemoEntity2.getContents(), "데모 내용을 변경한다.");
    }
}
