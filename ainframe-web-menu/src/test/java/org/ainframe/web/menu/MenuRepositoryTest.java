package org.ainframe.web.menu;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.ainframe.web.menu.domain.MenuEntity;
import org.ainframe.web.menu.repository.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("real")
@Transactional
public class MenuRepositoryTest {
    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void 기본적인조회테스트() {
        // 지연 로딩 테스트
        MenuEntity lazyMenuEntity = menuRepository.findOne("MENU00000000000ADMIN");
        assertEquals(lazyMenuEntity.getMenuIdx(), "MENU00000000000ADMIN");
        System.out.println(lazyMenuEntity.getMenuItemEntities().size());

        // 즉시 로딩 테스트
//        MenuEntity edgeMenuEntity = menuService.getMenu("MENU00000000000ADMIN");
//        assertNotEquals(Collections.emptyList(), edgeMenuEntity.getMenuItemEntities());
//        System.out.println(edgeMenuEntity.getMenuItemEntities());
    }
}
