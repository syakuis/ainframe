package org.ainframe.web.menu.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
@Builder
@Data
@Setter(AccessLevel.NONE)
public class Menu implements Serializable {
    private final String menuIdx;
    private final String menuName;
    private final List<MenuNode> menuNodes;
    private final List<MenuTree> menuTrees;

    public Menu(String menuIdx, String menuName, List<MenuNode> menuNodes, List<MenuTree> menuTrees) {
        this.menuIdx = menuIdx;
        this.menuName = menuName;
        this.menuNodes = menuNodes;
        this.menuTrees = menuTrees;
    }
}
