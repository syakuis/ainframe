package org.ainframe.web.menu;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.ainframe.context.MenuContext;
import org.ainframe.web.menu.config.MenuProperties;
import org.ainframe.web.menu.model.MenuNode;
import org.ainframe.web.menu.model.MenuTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MenuContextTest {
    @Autowired
    private MenuProperties menuProperties;

    @Autowired
    private MenuContext menuContext;

    @Test
    public void 현재메뉴찾기() {
        assertEquals(
            menuContext.getCurrentMenu(
                menuProperties.getDefaultUrlPattern(),"MENU0000000000000003", "/organization/a-1-1/list")
                .getUrl(), "/organization/a-1-1");
    }

    @Test
    public void 현재메뉴에해당하는최상위부모메뉴이하전부얻기() {
        MenuNode menuNode = menuContext.getCurrentMenu(
            menuProperties.getDefaultUrlPattern(),"MENU0000000000000003", "/organization/a-1-1/list");

        MenuTree menuTree = menuContext
            .getMenuTreeByTreeId("MENU0000000000000003", menuNode.getRootParentId());
        new MenuTreeDebug().displayMenuTree(Lists.newArrayList(menuTree), "MENU0000000000000003");
    }
}
