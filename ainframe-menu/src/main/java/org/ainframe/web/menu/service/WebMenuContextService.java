package org.ainframe.web.menu.service;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.ainframe.context.MenuContextService;
import org.ainframe.web.menu.config.MenuProperties;
import org.ainframe.web.menu.domain.MenuNodeEntity;
import org.ainframe.web.menu.domain.MenuTreeEntity;
import org.ainframe.web.menu.model.Menu;
import org.ainframe.web.menu.model.MenuNode;
import org.ainframe.web.menu.util.MenuNodeUtils;
import org.ainframe.web.menu.util.MenuTreeCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public Map<String, String> getAllMenuName() {
        List<MenuTreeEntity> menuTreeEntities = menuService.getMenuTrees();

        return Maps.newHashMap(Maps.transformValues(Maps.uniqueIndex(menuTreeEntities, new Function<MenuTreeEntity, String>() {
            @Override
            public String apply(MenuTreeEntity input) {
                return input.getMenuIdx();
            }
        }), new Function<MenuTreeEntity, String>() {
            @Override
            public String apply(MenuTreeEntity input) {
                return input.getMenuName();
            }
        }));
    }

    @Override
    public Menu getMenu(String menuIdx) {
        MenuTreeEntity menuTreeEntity = menuService.getMenuTreeByMenuIdx(menuIdx);

        List<MenuNodeEntity> menuNodeEntities = menuTreeEntity.getMenuNodeEntities();
        Collections.sort(menuNodeEntities);
        List<MenuNode> menuNodes = MenuNodeUtils.toMenus(menuNodeEntities);

        return new Menu(
            menuTreeEntity.getMenuIdx(),
            menuTreeEntity.getMenuName(),
            menuNodes,
            new MenuTreeCreator(menuNodes, menuProperties.getRootMenuId()).getMenuTrees());
    }
}
