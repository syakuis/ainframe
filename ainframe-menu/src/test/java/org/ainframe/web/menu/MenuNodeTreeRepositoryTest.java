package org.ainframe.web.menu;

import org.ainframe.web.menu.domain.MenuTreeEntity;
import org.ainframe.web.menu.repository.MenuTreeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MenuNodeTreeRepositoryTest {
    @Autowired
    private MenuTreeRepository menuTreeRepository;

    @Test
    public void 기본적인조회테스트() {
        // 지연 로딩 테스트
        MenuTreeEntity lazyMenuTreeEntity = menuTreeRepository.findOne("MENU00000000000ADMIN");
        assertEquals(lazyMenuTreeEntity.getMenuIdx(), "MENU00000000000ADMIN");
        System.out.println(lazyMenuTreeEntity.getMenuNodeEntities().size());

        // 즉시 로딩 테스트
//        MenuTreeEntity edgeMenuEntity = menuService.getMenu("MENU00000000000ADMIN");
//        assertNotEquals(Collections.emptyList(), edgeMenuEntity.getMenuItemEntities());
//        System.out.println(edgeMenuEntity.getMenuItemEntities());
    }
}
