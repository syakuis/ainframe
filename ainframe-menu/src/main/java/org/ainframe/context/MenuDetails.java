package org.ainframe.context;

import java.io.Serializable;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
@Builder
@Data
@Setter(AccessLevel.NONE)
public class MenuDetails implements Serializable {
    private final String menuIdx;
    private final String menuName;
    private final List<Menu> menus;
    private final List<MenuTree> menuTrees;

    public MenuDetails(String menuIdx, String menuName, List<Menu> menus, List<MenuTree> menuTrees) {
        this.menuIdx = menuIdx;
        this.menuName = menuName;
        this.menus = menus;
        this.menuTrees = menuTrees;
    }
}
