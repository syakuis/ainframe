package org.ainframe.context.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
@AllArgsConstructor
@Data
@Setter(AccessLevel.NONE)
public class Menu {
    private final String treeId;
    /**
     * 부모 id
     */
    private final String parentId;
    /**
     * 최상위 부모 id
     */
    private final String rootParentId;
    private final String treeName;
    private final String url;
}
