package org.ainframe.web.menu.service;

import java.util.Collections;
import java.util.List;

import org.ainframe.context.MenuContextService;
import org.ainframe.context.model.Menu;
import org.ainframe.context.model.MenuDetails;
import org.ainframe.web.menu.domain.MenuEntity;
import org.ainframe.web.menu.domain.MenuItemEntity;
import org.ainframe.web.menu.util.MenuTreeCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
@Service
public class WebMenuContextService implements MenuContextService {
    private MenuService menuService;

    @Value("rootMenuId")
    private String rootMenuId;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public MenuDetails getMenuDetails(String menuIdx) {
        MenuEntity menuEntity = menuService.getMenuWithMenuItem(menuIdx);

        List<MenuItemEntity> menuItemEntities = menuEntity.getMenuItemEntities();
        Collections.sort(menuItemEntities);
        List<Menu> menus = MenuEntity.transformByMenuItemEntities(menuItemEntities);

        return MenuDetails.builder()
            .menuIdx(menuEntity.getMenuIdx())
            .menuName(menuEntity.getMenuName())
            .menus(menus)
            .menuTrees(new MenuTreeCreator(menus, rootMenuId).getMenuTrees())
            .build();
    }
}
