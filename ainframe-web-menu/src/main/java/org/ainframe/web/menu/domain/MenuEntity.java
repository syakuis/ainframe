package org.ainframe.web.menu.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.ainframe.context.model.Menu;
import org.ainframe.context.model.MenuTree;
import org.hibernate.annotations.GenericGenerator;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
@Entity
@Table(name = "MENU")
@NamedEntityGraph(
    name = "MenuEntity.menuItemEntities", attributeNodes = @NamedAttributeNode("menuItemEntities"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuEntity implements Serializable {
    @Column(name = "MENU_IDX", nullable = false, length = 20)
    @Id
    @SequenceGenerator(
        name = "MENU_IDX_GEN",
        sequenceName = "MENU_IDX_SEQ",
        allocationSize = 1
    )
    @GeneratedValue(
        generator = "MENU_IDX_GEN",
        strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
        name = "MENU_IDX_GEN",
        strategy = "org.ainframe.data.jpa.StringSequenceIdentifier",
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "sequence_name", value = "MENU_IDX_SEQ"),
            @org.hibernate.annotations.Parameter(
                name = "sequence_prefix", value = "MENU"),
        }
    )
    private String menuIdx;

    @Column(name = "MENU_NAME", nullable = false)
    private String menuName;

    @Column(name = "REG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "MOD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @OneToMany(targetEntity = MenuItemEntity.class)
    @JoinColumn(name = "MENU_IDX")
    private List<MenuItemEntity> menuItemEntities;

    public static List<Menu> transformByMenuItemEntities(List<MenuItemEntity> menuItem) {
        return Lists.transform(menuItem, new Function<MenuItemEntity, Menu>() {
            @Override
            public Menu apply(MenuItemEntity input) {
                return MenuItemEntity.transform(input);
            }
        });
    }
}
