package org.ainframe.web.menu.util;

import java.util.*;

import org.ainframe.core.util.Label;
import org.ainframe.web.menu.domain.MenuItemEntity;
import org.ainframe.web.menu.model.MenuTree;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import lombok.Getter;

/**
 * 목록으로 구성된 메뉴 항목들을 트리 형태로 변경한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
public class MenuTreeUtils {
    private final List<MenuItemEntity> menuItemEntities;
    private final String rootId;
    private Map<String, Integer> mapping;
    @Getter
    private List<MenuTree> menus;

    public MenuTreeUtils(List<MenuItemEntity> menuItemEntities, String rootId) {
        this.menuItemEntities = menuItemEntities;
        this.rootId = rootId;
        this.mapping();
        this.menus = this.create(rootId);
    }

    /**
     * 자식이 있는 부모만 hashMap 에 담는 다.
     */
    private void mapping() {
        this.mapping = new HashMap<>();
        for (MenuItemEntity menuItemEntity : menuItemEntities) {
            String parentId = menuItemEntity.getParentId();
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
     * @param rootId 최상위 아이디
     * @return List
     */
    public List<MenuTree> create(String rootId) {
        return this.create(rootId, 0, 0, null);
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
        int count = menuItemEntities.size();
        for (int i = index; i < count; i++) {
            MenuItemEntity menuItemEntity = menuItemEntities.get(i);
            String aTreeId = menuItemEntity.getTreeId();
            String aParentId = menuItemEntity.getParentId();

            List<String> aBreadcrumb = this.breadcrumb(breadcrumb, aDepth, aTreeId);

            if (Objects.equals(parentId, aParentId) && mapping.containsKey(aParentId)) {
                result.add(MenuTree.builder()
                    .treeName(menuItemEntity.getTreeName())
                    .parentId(aParentId)
                    .treeId(aTreeId)
                    .treeDepth(aDepth)
                    .url(menuItemEntity.getUrl())
                    .breadcrumb(aBreadcrumb)
                    .menus(create(aTreeId, i+1, aDepth, aBreadcrumb))
                    .build());
            }
        }

        return result;
    }

    /**
     * 디버그 전용이다. menu mapping 정보를 출력한다.
     */
    public void displayMapping() {
        Iterator<String> keys = mapping.keySet().iterator();
        Label label = new Label().title("MENU TREE MAPPING");
        while (keys.hasNext()) {
            String key = keys.next();
            label.first(key).add("=").add(mapping.get(key));
        }
        label.debug();
    }

    /**
     * 디버그 전용이다. menuTree 구조와 정보를 출력한다.
     */
    public void displayMenuTree() {
        Label label = new Label();
        label.title("MENU TREE")
            .first("treeId")
            .add(StringUtils.repeat(" ", 4)).add("breadcrumb");
        displayMenuTreeItem(label, menus);
        label.debug();
    }

    private void displayMenuTreeItem(Label label, List<MenuTree> menus) {
        for (MenuTree menu : menus) {
            StringBuilder builder = new StringBuilder();
            builder.append(StringUtils.rightPad(menu.getTreeId(), 10))
                .append(StringUtils.rightPad(StringUtils.join(menu.getBreadcrumb(), " > "), 30))
                .append("|")
                .append(StringUtils.repeat("    ", menu.getTreeDepth()))
                .append("└─ ")
                .append(menu.getTreeName());

            label.first(builder.toString());
            List<MenuTree> aMenus = menu.getMenus();
            if (!aMenus.isEmpty()) {
                displayMenuTreeItem(label, aMenus);
            }
        }
    }
}
