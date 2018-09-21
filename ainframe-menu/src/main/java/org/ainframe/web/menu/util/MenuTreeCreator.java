package org.ainframe.web.menu.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.ainframe.web.menu.model.MenuNode;
import org.ainframe.web.menu.model.MenuTree;

import java.util.*;

/**
 * 목록으로 구성된 메뉴 항목들을 트리 형태로 변경한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
@EqualsAndHashCode
@ToString
public class MenuTreeCreator {
    private final List<MenuNode> menuNodes;
    private Map<String, Integer> mapping;
    private List<MenuTree> menuTrees;

    public MenuTreeCreator(List<MenuNode> menuNodes, String rootMenuId) {
        this.menuNodes = menuNodes;
        this.mapping();
        this.menuTrees = this.create(rootMenuId);
    }

    /**
     * 트리 메뉴 맵핑 정보
     * @return Map
     */
    public Map<String, Integer> getMapping() {
        return Maps.newHashMap(mapping);
    }

    /**
     * 트리 메뉴 가져오기
     * @return List
     */
    public List<MenuTree> getMenuTrees() {
        return Lists.newArrayList(menuTrees);
    }

    /**
     * 자식이 있는 부모만 hashMap 에 담는 다.
     */
    private void mapping() {
        this.mapping = new HashMap<>();
        for (MenuNode menuNode : menuNodes) {
            String parentId = menuNode.getParentId();
            if (parentId != null && this.mapping.containsKey(parentId)) {
                this.mapping.put(parentId, this.mapping.get(parentId) + 1);
            } else {
                this.mapping.put(parentId, 1);
            }
        }
    }

    /**
     * 사이트 경로를 만든다.
     * @param breadcrumb 이전 사이트 정보
     * @param depth 트리 깊이
     * @param treeId 트리
     * @return List 사이트 경로
     */
    private List<String> breadcrumb(List<String> breadcrumb, int depth, String treeId) {
        List<String> result;

        if (depth == 1) {
            result = new ArrayList<>();
        } else {
            result = Lists.newArrayList(breadcrumb);
        }

        result.add(treeId);

        return result;
    }

    /**
     * 목록 메뉴를 트리형태로 만든다.
     * @param rootMenuId 최상위 아이디
     * @return List
     */
    private List<MenuTree> create(String rootMenuId) {
        return this.create(rootMenuId, 0, 0, null);
    }

    /**
     * 목록 메뉴를 트리형태로 만든다.
     * @param parentId 부모
     * @param index 읽을 순서
     * @param depth 깊이
     * @return List 메뉴 트리
     */
    private List<MenuTree> create(String parentId, int index, int depth, List<String> breadcrumb) {
        int aDepth = depth + 1;
        List<MenuTree> result = new ArrayList<>();
        int count = menuNodes.size();
        for (int i = index; i < count; i++) {
            MenuNode menuNode = menuNodes.get(i);
            String aTreeId = menuNode.getTreeId();
            String aParentId = menuNode.getParentId();

            List<String> aBreadcrumb = this.breadcrumb(breadcrumb, aDepth, aTreeId);

            if (Objects.equals(parentId, aParentId) && mapping.containsKey(aParentId)) {
                result.add(MenuTree.builder()
                    .treeName(menuNode.getTreeName())
                    .parentId(aParentId)
                    .rootParentId(menuNode.getRootParentId())
                    .treeId(aTreeId)
                    .treeDepth(aDepth)
                    .url(menuNode.getUrl())
                    .breadcrumb(aBreadcrumb)
                    .menus(create(aTreeId, i+1, aDepth, aBreadcrumb))
                    .build());
            }
        }

        return result;
    }
}
