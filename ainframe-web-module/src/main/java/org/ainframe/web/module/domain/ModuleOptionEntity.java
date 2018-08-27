package org.ainframe.web.module.domain;

import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 25.
 */
@Entity
@Table(
    name = "MODULE_OPTION",
    uniqueConstraints = @UniqueConstraint(
        name = "MODULE_OPTION_UQ_1",
        columnNames = { "MODULE_IDX", "NAME" }
    ))
@SequenceGenerator(
        name = "MODULE_OPTION_SRL_GEN",
        sequenceName = "MODULE_OPTION_SRL_SEQ",
        allocationSize = 1)
public class ModuleOptionEntity {
    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "MODULE_OPTION_SRL_GEN", strategy = GenerationType.SEQUENCE)
    @Column(name = "MODULE_OPTION_SRL")
    private long moduleOptionIdx;

    @Getter
    @Setter
    @Column(name = "MODULE_IDX", nullable = false)
    private String moduleIdx;

    @Getter
    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "VALUE")
    private String value;

    @Getter
    @Setter
    @Column(name = "TITLE")
    private String title;

    @Getter
    @Setter
    @Column(name = "REG_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    // todo 등록일 및 생성일 필드명 개선
    private Date regDate;

    public ModuleOptionEntity() {
    }

    public ModuleOptionEntity(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @PrePersist
    public void prePersist() {
        this.regDate = new Date();
    }

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
