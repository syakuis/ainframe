package org.ainframe.web.menu.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
@RequiredArgsConstructor
@Data
@Setter(AccessLevel.NONE)
public class MenuNode implements Serializable {
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
