package org.ainframe.web.menu.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
@Entity
@Table(name = "MENU")
@NamedEntityGraph(
    name = "MenuDetailsEntity.menuItemEntities", attributeNodes = @NamedAttributeNode("menuItemEntities"))
@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDetailsEntity implements Serializable {
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

    @OneToMany(targetEntity = MenuEntity.class)
    @JoinColumn(name = "MENU_IDX")
    private List<MenuEntity> menuEntities;
}
