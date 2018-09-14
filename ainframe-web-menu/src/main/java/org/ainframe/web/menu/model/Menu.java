package org.ainframe.web.menu.model;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import lombok.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Menu {
    private String treeId;
    private String parentId;
    private String treeName;
    private int treeDepth;
    private String url;
    /**
     * 메뉴 경로
     */
    private List<String> breadcrumb;

    public static List<String> createBreadcrumb(String... treeId) {
        return Lists.newArrayList(treeId);
    }
}
