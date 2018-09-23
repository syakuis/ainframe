package org.ainframe.web.module.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * todo 테이블 필드명 다시 만들기
 * todo 순서를 정렬할 수 있게 필드를 추가한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 25.
 */
@Entity
@Table(name = "MODULE_OPTIONS", uniqueConstraints = @UniqueConstraint(columnNames = {"MODULE_IDX", "OPTIONS_NAME"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleOptionEntity implements Serializable {
    @SequenceGenerator(
        name = "MODULE_OPTIONS_SRL_GEN",
        sequenceName = "MODULE_OPTIONS_SRL_SEQ",
        allocationSize = 1)
    @GeneratedValue(generator = "MODULE_OPTIONS_SRL_GEN", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "MODULE_OPTIONS_SRL")
    private long moduleOptionIdx;

    @Column(name = "MODULE_IDX", nullable = false)
    private String moduleIdx;

    @Column(name = "OPTIONS_NAME", nullable = false)
    private String name;

    @Column(name = "OPTIONS_VALUE")
    private String value;

    @Column(name = "OPTIONS_COMMENT")
    private String title;

    /*@Column(name = "REG_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    // todo 등록일 및 생성일 필드명 개선
    private Date regDate;*/

/*    @PrePersist
    public void prePersist() {
        this.regDate = new Date();
    }*/
}
