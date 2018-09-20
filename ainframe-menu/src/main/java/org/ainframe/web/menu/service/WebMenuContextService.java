package org.ainframe.web.menu.service;

import java.util.Collections;
import java.util.List;

import org.ainframe.context.Menu;
import org.ainframe.context.MenuContextService;
import org.ainframe.context.MenuDetails;
import org.ainframe.web.menu.config.MenuProperties;
import org.ainframe.web.menu.domain.MenuDetailsEntity;
import org.ainframe.web.menu.domain.MenuEntity;
import org.ainframe.web.menu.util.MenuItemUtils;
import org.ainframe.web.menu.util.MenuTreeCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
@Service
@Transactional
public class WebMenuContextService implements MenuContextService {
    private MenuProperties menuProperties;
    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setMenuProperties(MenuProperties menuProperties) {
        this.menuProperties = menuProperties;
    }

    @Override
    public MenuDetails getMenuDetails(String menuIdx) {
        MenuDetailsEntity menuDetailsEntity = menuService.getMenuWithMenuItem(menuIdx);

        List<MenuEntity> menuItemEntities = menuDetailsEntity.getMenuItemEntities();
        Collections.sort(menuItemEntities);
        List<Menu> menus = MenuItemUtils.toMenus(menuItemEntities);

        return MenuDetails.builder()
            .menuIdx(menuDetailsEntity.getMenuIdx())
            .menuName(menuDetailsEntity.getMenuName())
            .menus(menus)
            .menuTrees(new MenuTreeCreator(menus, menuProperties.getRootMenuId()).getMenuTrees())
            .build();
    }
}
