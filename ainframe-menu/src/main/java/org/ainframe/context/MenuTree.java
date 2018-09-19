package org.ainframe.context;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */

@Data
@Setter(AccessLevel.NONE)
public class MenuTree extends Menu {
    private final int treeDepth;
    /**
     * 메뉴 경로
     */
    private final List<String> breadcrumb;
    private final List<MenuTree> menus;

    @Builder
    public MenuTree(String treeId, String parentId, String rootParentId, String treeName, String url, int treeDepth, List<String> breadcrumb, List<MenuTree> menus) {
        super(treeId, parentId, rootParentId, treeName, url);
        this.treeDepth = treeDepth;
        this.breadcrumb = breadcrumb;
        this.menus = menus;
    }
}
