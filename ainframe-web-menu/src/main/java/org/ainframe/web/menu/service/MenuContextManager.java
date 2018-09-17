package org.ainframe.web.menu.service;

import java.util.List;
import java.util.Objects;

import org.ainframe.context.MenuContextService;
import org.ainframe.context.model.Menu;
import org.ainframe.context.model.MenuDetails;
import org.ainframe.context.model.MenuTree;
import org.ainframe.web.menu.config.MenuProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
@Component
public class MenuContextManager {
    private MenuProperties menuProperties;
    private MenuContextService menuContextService;
    private PathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    public void setMenuProperties(MenuProperties menuProperties) {
        this.menuProperties = menuProperties;
    }

    @Autowired
    public void setMenuContextService(MenuContextService menuContextService) {
        this.menuContextService = menuContextService;
    }

    public MenuDetails getMenuDetails(String menuIdx) {
        return menuContextService.getMenuDetails(menuIdx);
    }

    public List<Menu> getMenus(String menuIdx) {
        return this.getMenuDetails(menuIdx).getMenus();
    }

    public List<MenuTree> getMenuTree(String menuIdx) {
        return this.getMenuDetails(menuIdx).getMenuTrees();
    }

    /**
     * 현재 요청한 경로(Request path)에 맞는 메뉴를 찾아 반환한다.
     */
    public Menu getCurrentMenu(final String menuIdx, final String path) {
        return Iterables.find(Lists.reverse(this.getMenus(menuIdx)), new Predicate<Menu>() {
            @Override
            public boolean apply(Menu input) {
                return input.getUrl() != null
                    && pathMatcher.match(input.getUrl() + menuProperties.getDefaultUrlPattern(), path);
            }
        });
    }

    public MenuTree getMenuTreeByTreeId(final String menuIdx, final String treeId) {
        return Iterables.find(this.getMenuTree(menuIdx), new Predicate<MenuTree>() {
            @Override
            public boolean apply(MenuTree input) {
                return Objects.equals(input.getTreeId(), treeId);
            }
        });
    }
}
