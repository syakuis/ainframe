package org.ainframe.web.menu.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.ainframe.context.model.Menu;
import org.ainframe.context.model.MenuTree;
import org.ainframe.web.menu.MenuTreeDebug;
import org.ainframe.web.menu.domain.MenuItemEntity;
import org.ainframe.web.menu.repository.MenuItemRepository;
import org.ainframe.web.menu.repository.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("real")
@Transactional
public class MenuContextManagerTest {
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private MenuContextManager menuContextManager;

    @Test
    public void 현재메뉴찾기() {
        assertEquals(
            menuContextManager.getCurrentMenu("MENU0000000000000003", "/organization/a-1-1/list").getUrl(),
            "/organization/a-1-1");
    }

    @Test
    public void 현재메뉴에해당하는최상위부모메뉴이하전부얻기() {
        Menu menu = menuContextManager.getCurrentMenu("MENU0000000000000003", "/organization/a-1-1/list");

        assertEquals(
            MenuItemEntity.transform(
                menuItemRepository.findOneByMenuIdxAndTreeId("MENU0000000000000003", menu.getTreeId())),
            menu);

        assertNotNull(menuItemRepository.findOneByMenuIdxAndTreeId(
            "MENU0000000000000003", menu.getRootParentId()));

        MenuTree menuTree = menuContextManager
            .getMenuTreeByTreeId("MENU0000000000000003", menu.getRootParentId());
        new MenuTreeDebug().displayMenuTree(Lists.newArrayList(menuTree), "MENU0000000000000003");
    }
}
