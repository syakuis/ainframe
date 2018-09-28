package org.syaku.exemple;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 28.
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.syaku.exemple.app.demo.domain.DemoEntity;
import org.syaku.exemple.app.demo.repository.DemoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class DemoRepositoryTest {
//    @Autowired
//    private DemoRepository demoRepository;

    @Value("${ainframe.data.jpa.enable}")
    private boolean enable;

    @Test
    public void save() {
        System.out.println(enable);
//        demoRepository.save(DemoEntity.builder().subject("안녕하세요.").contents("데모를 테스트합니다.").build());
    }
}
