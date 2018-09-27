package org.ainframe.web.layout.domain;

import lombok.*;
import org.ainframe.context.Layout;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
@Entity
@Table(name = "LAYOUT")
@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LayoutEntity implements Layout {
    @Column(name = "LAYOUT_IDX", nullable = false, length = 20)
    @Id
    @SequenceGenerator(
        name = "LAYOUT_IDX_GEN",
        sequenceName = "LAYOUT_IDX_SEQ",
        allocationSize = 1
    )
    @GeneratedValue(
        generator = "LAYOUT_IDX_GEN",
        strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
        name = "LAYOUT_IDX_GEN",
        strategy = "org.ainframe.data.jpa.StringSequenceIdentifier",
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "sequence_name", value = "LAYOUT_IDX_SEQ"),
            @org.hibernate.annotations.Parameter(
                name = "sequence_prefix", value = "LAOUT"),
        }
    )
    private String layoutIdx;
    @Column(name = "MENU_IDX")
    private String menuIdx;
    @Column(name = "LAYOUT", nullable = false)
    private String layoutName;
    @Column(name = "TITLE", nullable = false)
    private String title;
    /**
     * 기본적으로 layout.ftl 을 사용하지만 원한다면 변경할 수 있다. 템플릿을 다르게 하면서 자원은 공유할 수 있다.
     */
    @Column(name = "LAYOUT_TEMPLATE")
    private String layoutTemplate;
    @Column(name = "HEAD_SCRIPT")
    private String headScript;
    @Column(name = "REG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}
