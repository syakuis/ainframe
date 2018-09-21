package org.ainframe.context;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.ainframe.web.menu.model.Menu;
import org.ainframe.web.menu.model.MenuNode;
import org.ainframe.web.menu.model.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;
import java.util.Objects;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
@Component
public class MenuContext {
    private MenuContextService menuContextService;
    private PathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    @Qualifier("cacheMenuContextService")
    public void setMenuContextService(MenuContextService menuContextService) {
        this.menuContextService = menuContextService;
    }

    public Menu getMenu(String menuIdx) {
        return menuContextService.getMenu(menuIdx);
    }

    public List<MenuNode> getMenuNodes(String menuIdx) {
        return this.getMenu(menuIdx).getMenuNodes();
    }

    public List<MenuTree> getMenuTree(String menuIdx) {
        return this.getMenu(menuIdx).getMenuTrees();
    }

    /**
     * 현재 요청한 경로(Request path)에 맞는 메뉴를 찾아 반환한다.
     */
    public MenuNode getCurrentMenu(final String defaultUrlPattern, final String menuIdx, final String path) {
        return Iterables.find(Lists.reverse(this.getMenuNodes(menuIdx)), new Predicate<MenuNode>() {
            @Override
            public boolean apply(MenuNode input) {
                return input.getUrl() != null
                    && pathMatcher.match(input.getUrl() + defaultUrlPattern, path);
            }
        });
    }

    /**
     * treeId 의 메뉴를 포함하여 그 아래 모든 자식 메뉴들까지 반환한다.
     * @param menuIdx 메뉴번호
     * @param treeId 메뉴id
     * @return MenuTree
     */
    public MenuTree getMenuTreeByTreeId(final String menuIdx, final String treeId) {
        return Iterables.find(this.getMenuTree(menuIdx), new Predicate<MenuTree>() {
            @Override
            public boolean apply(MenuTree input) {
                return Objects.equals(input.getTreeId(), treeId);
            }
        });
    }
}
