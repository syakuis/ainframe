package org.ainframe.web.menu.model;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
@Getter
@EqualsAndHashCode
@ToString
public class MenuTree extends Menu {
    private List<MenuTree> menus;

    @Builder
    public MenuTree(String treeId, String parentId, String treeName, int treeDepth, String url, List<String> breadcrumb, List<MenuTree> menus) {
        super(treeId, parentId, treeName, treeDepth, url, breadcrumb);
        this.menus = menus;
    }
}
