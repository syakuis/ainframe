package org.ainframe.web.menu.util;

import java.util.List;

import org.ainframe.context.Menu;
import org.ainframe.context.MenuTree;
import org.ainframe.web.menu.domain.MenuEntity;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * {@link MenuEntity} 를 {@link Menu} 클래스 타입으로 변경한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
public final class MenuItemUtils {
    public static List<Menu> toMenus(List<MenuEntity> menuItemEntities) {
        return Lists.newArrayList(Lists.transform(menuItemEntities, new Function<MenuEntity, Menu>() {
            @Override
            public Menu apply(MenuEntity input) {
                return toMenu(input);
            }
        }));
    }

    public static Menu toMenu(MenuEntity menuItem) {
        return MenuTree.builder()
            .treeId(menuItem.getTreeId())
            .parentId(menuItem.getParentId())
            .rootParentId(menuItem.getRootParentId())
            .treeName(menuItem.getTreeName())
            .url(menuItem.getUrl())
            .build();
    }
}
