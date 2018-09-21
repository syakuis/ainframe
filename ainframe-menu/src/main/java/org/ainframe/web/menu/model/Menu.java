package org.ainframe.web.menu.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
@RequiredArgsConstructor
@Data
@Setter(AccessLevel.NONE)
public class Menu implements Serializable {
    private final String menuIdx;
    private final String menuName;
    private final List<MenuNode> menuNodes;
    private final List<MenuTree> menuTrees;
}
