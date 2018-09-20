package org.ainframe.web.menu;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ainframe.core.util.Label;
import org.ainframe.web.menu.config.MenuProperties;
import org.ainframe.web.menu.domain.MenuDetailsEntity;
import org.ainframe.web.menu.domain.MenuEntity;
import org.ainframe.web.menu.service.MenuService;
import org.ainframe.web.menu.util.MenuItemUtils;
import org.ainframe.web.menu.util.MenuTreeCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 12.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("real")
@Transactional
public class MenuTreeCreatorTest {
    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuProperties menuProperties;

    /**
     * 디버그 전용이다. menu mapping 정보를 출력한다.
     */
    public void displayMapping(Map mapping) {
        Iterator<String> keys = mapping.keySet().iterator();
        Label label = new Label().title("MENU TREE MAPPING");
        while (keys.hasNext()) {
            String key = keys.next();
            label.first(key).add("=").add(mapping.get(key));
        }
        label.debug();
    }

    private List<MenuEntity> getMenuItemEntities() {
        MenuDetailsEntity menuDetailsEntity = menuService.getMenuWithMenuItem("MENU0000000000000003");
        return menuDetailsEntity.getMenuEntities();
    }

    @Test
    public void 메뉴를트리형태로만들기() {
        List<MenuEntity> menuEntities = getMenuItemEntities();
        Collections.sort(menuEntities);
        for (MenuEntity menuEntity : menuEntities) {
            log.debug("{} = {} : {}", menuEntity.getTreeOrder(), menuEntity.getTreeName(), menuEntity.getParentId());
        }

        MenuTreeCreator menuTree = new MenuTreeCreator(
            MenuItemUtils.toMenus(menuEntities), menuProperties.getRootMenuId());
        this.displayMapping(menuTree.getMapping());
        new MenuTreeDebug().displayMenuTree(menuTree.getMenuTrees(), menuProperties.getRootMenuId());
    }
}
