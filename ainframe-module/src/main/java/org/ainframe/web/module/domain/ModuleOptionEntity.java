package org.ainframe.web.module.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * todo 테이블 필드명 다시 만들기
 * todo 순서를 정렬할 수 있게 필드를 추가한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 25.
 */
@Entity
@Table(name = "MODULE_OPTIONS")
@Getter
@Setter
public class ModuleOptionEntity implements Serializable {
    /*@SequenceGenerator(
        name = "MODULE_OPTION_SRL_GEN",
        sequenceName = "MODULE_OPTION_SRL_SEQ",
        allocationSize = 1)
    @GeneratedValue(generator = "MODULE_OPTION_SRL_GEN", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "MODULE_OPTION_SRL")
    private long moduleOptionIdx;*/

    @Id
    @Column(name = "MODULE_IDX", nullable = false)
    private String moduleIdx;

    @Id
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

    public ModuleOptionEntity() {
    }

    public ModuleOptionEntity(String name, String value) {
        this.name = name;
        this.value = value;
    }

/*    @PrePersist
    public void prePersist() {
        this.regDate = new Date();
    }*/

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
