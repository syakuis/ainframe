package org.ainframe.web.menu;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.ainframe.web.menu.domain.MenuDetailsEntity;
import org.ainframe.web.menu.repository.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MenuRepositoryTest {
    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void 기본적인조회테스트() {
        // 지연 로딩 테스트
        MenuDetailsEntity lazyMenuDetailsEntity = menuRepository.findOne("MENU00000000000ADMIN");
        assertEquals(lazyMenuDetailsEntity.getMenuIdx(), "MENU00000000000ADMIN");
        System.out.println(lazyMenuDetailsEntity.getMenuItemEntities().size());

        // 즉시 로딩 테스트
//        MenuDetailsEntity edgeMenuEntity = menuService.getMenu("MENU00000000000ADMIN");
//        assertNotEquals(Collections.emptyList(), edgeMenuEntity.getMenuItemEntities());
//        System.out.println(edgeMenuEntity.getMenuItemEntities());
    }
}
