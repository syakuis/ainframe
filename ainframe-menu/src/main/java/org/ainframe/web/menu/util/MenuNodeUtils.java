package org.ainframe.web.menu.util;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.ainframe.web.menu.domain.MenuNodeEntity;
import org.ainframe.web.menu.model.MenuNode;
import org.ainframe.web.menu.model.MenuTree;

import java.util.List;

/**
 * {@link MenuNodeEntity} 를 {@link MenuNode} 클래스 타입으로 변경한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
public final class MenuNodeUtils {
    public static List<MenuNode> toMenus(List<MenuNodeEntity> menuItemEntities) {
        return Lists.newArrayList(Lists.transform(menuItemEntities, new Function<MenuNodeEntity, MenuNode>() {
            @Override
            public MenuNode apply(MenuNodeEntity input) {
                return toMenu(input);
            }
        }));
    }

    public static MenuNode toMenu(MenuNodeEntity menuItem) {
        return MenuTree.builder()
            .treeId(menuItem.getTreeId())
            .parentId(menuItem.getParentId())
            .rootParentId(menuItem.getRootParentId())
            .treeName(menuItem.getTreeName())
            .url(menuItem.getUrl())
            .build();
    }
}
