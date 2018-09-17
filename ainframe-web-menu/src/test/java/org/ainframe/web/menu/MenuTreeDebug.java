package org.ainframe.web.menu;

import java.util.List;

import org.ainframe.context.model.MenuTree;
import org.ainframe.core.util.Label;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
public class MenuTreeDebug {
    /**
     * 디버그 전용이다. menuTree 구조와 정보를 출력한다.
     */
    public void displayMenuTree(List<MenuTree> menus, String rootMenuId) {
        Label label = new Label();
        label.title("MENU TREE")
            .first("rootMenuId : ").add(rootMenuId)
            .first("treeId")
            .add(StringUtils.repeat(" ", 4)).add("breadcrumb");
        displayMenuTreeItem(label, menus);
        label.debug();
    }

    private void displayMenuTreeItem(Label label, List<MenuTree> menus) {
        for (MenuTree menu : menus) {
            StringBuilder builder = new StringBuilder();
            builder
                .append(StringUtils.rightPad(menu.getTreeId(), 10))
                .append(StringUtils.rightPad(StringUtils.join(menu.getBreadcrumb(), " > "), 30))
                .append("|")
                .append(StringUtils.repeat("    ", menu.getTreeDepth()))
                .append("└─ ")
                .append(menu.getTreeName())
                .append(" ____ ")
                .append(menu.getUrl());

            label.first(builder.toString());
            List<MenuTree> aMenus = menu.getMenus();
            if (!aMenus.isEmpty()) {
                displayMenuTreeItem(label, aMenus);
            }
        }
    }
}
