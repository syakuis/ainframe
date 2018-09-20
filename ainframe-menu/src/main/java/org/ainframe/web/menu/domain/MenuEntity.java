package org.ainframe.web.menu.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.ainframe.core.data.enums.YesOrNo;
import org.ainframe.web.menu.enums.UrlType;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
@Entity
@Table(name = "MENU_TREE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuEntity implements Serializable, Comparable<MenuEntity> {
    @Column(name = "MENU_IDX")
    private String menuIdx;

    @Column(name = "MENU_TREE_IDX", nullable = false, length = 20)
    @Id
    @SequenceGenerator(
        name = "MENU_TREE_IDX_GEN",
        sequenceName = "MENU_TREE_IDX_SEQ",
        allocationSize = 1
    )
    @GeneratedValue(
        generator = "MENU_TREE_IDX_GEN",
        strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
        name = "MENU_TREE_IDX_GEN",
        strategy = "org.ainframe.data.jpa.StringSequenceIdentifier",
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "sequence_name", value = "MENU_TREE_IDX_SEQ"),
            @org.hibernate.annotations.Parameter(
                name = "sequence_prefix", value = "MENUT"),
        }
    )
    private String menuTreeIdx;
    @Column(name = "TREE_ID")
    private String treeId;
    @Column(name = "TREE_NAME")
    private String treeName;

    /**
     * 최상위 부모 id
     */
    @Column(name = "GROUP_ID")
    private String rootParentId;
    @Column(name = "PARENT_ID")
    private String parentId;
    // todo 제거한다.
    // todo String list 변경할 수 있나? 변경한다.
    @Column(name = "PARENTS_ID")
    private String parentsId;
    // todo 제거한다.
    @Column(name = "TREE_DEPTH")
    private int treeDepth;

    /**
     * 트리 구조의 아이템 순서 번호이다.
     *                  (treeOrder)
     * |- 루트 -------------> 1
     * |  L 메뉴1 ----------> 2
     * |  L 메뉴2 ----------> 3
     * |    L 자식1 --------> 4
     */
    @Column(name = "TREE_ORDER")
    private int treeOrder;

    @Column(name = "URL")
    private String url;

    // todo string 으로 저장한다.
    @Column(name = "URL_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private UrlType urlType;
    @Column(name = "URL_WINDOW")
    private String urlWindow;
    /**
     * Anti Style 이용하여 대상이 되는 모든 경로를 현재 메뉴로 인정한다.
     * 기본 값은 {@link MenuEntity#getUrl()}/** 사용된다.
     */
    @Column(name = "URL_PATTERN")
    private String urlPattern;
    @Column(name = "IMAGE_BASIC")
    private String basicImage;
    @Column(name = "IMAGE_OVER")
    private String overImage;
    @Column(name = "IMAGE_SELECT")
    private String selectImage;
    @Column(name = "TITLE_IMAGE")
    private String titleImage;

    // todo YN 로 변경하고 EnumType.STRING 설정한다.
    @Column(name = "IS_MAIN_DISPLAY")
    @Enumerated(EnumType.ORDINAL)
    private YesOrNo displayHeader;

    // todo YN 로 변경하고 EnumType.STRING 설정한다.
    @Column(name = "IS_TREE_MENU")
    @Enumerated(EnumType.ORDINAL)
    private YesOrNo useTreeStyle;

    // todo YN 로 변경하고 EnumType.STRING 설정한다.
    @Column(name = "IS_HIDDEN")
    @Enumerated(EnumType.ORDINAL)
    private YesOrNo hidden;

    @Override
    public int compareTo(MenuEntity o) {
        return this.treeOrder - o.treeOrder;
    }
}
