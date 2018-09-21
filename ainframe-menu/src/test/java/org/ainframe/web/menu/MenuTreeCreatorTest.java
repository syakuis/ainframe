package org.ainframe.web.menu;

import lombok.extern.slf4j.Slf4j;
import org.ainframe.core.util.Label;
import org.ainframe.web.menu.config.MenuProperties;
import org.ainframe.web.menu.model.MenuNode;
import org.ainframe.web.menu.service.CacheMenuContextService;
import org.ainframe.web.menu.util.MenuTreeCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 12.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MenuTreeCreatorTest {
    @Autowired
    private CacheMenuContextService cacheMenuContextService;

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

    @Test
    public void 메뉴를트리형태로만들기() {
        List<MenuNode> menuNodes = cacheMenuContextService.getMenu("MENU0000000000000003").getMenuNodes();
        for (MenuNode menuNode : menuNodes) {
            log.debug("{} = {}", menuNode.getTreeName(), menuNode.getParentId());
        }

        MenuTreeCreator menuTree = new MenuTreeCreator(menuNodes, menuProperties.getRootMenuId());
        this.displayMapping(menuTree.getMapping());
        new MenuTreeDebug().displayMenuTree(menuTree.getMenuTrees(), menuProperties.getRootMenuId());
    }
}
