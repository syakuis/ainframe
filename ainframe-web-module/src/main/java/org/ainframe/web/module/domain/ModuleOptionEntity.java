package org.ainframe.web.module.domain;

import javax.persistence.*;

import org.ainframe.web.module.domain.support.ModuleOptionPKey;
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
@Table(name = "MODULE_OPTIONS")
@SequenceGenerator(
        name = "MODULE_OPTION_SRL_GEN",
        sequenceName = "MODULE_OPTION_SRL_SEQ",
        allocationSize = 1)
@IdClass(ModuleOptionPKey.class)
public class ModuleOptionEntity {
//    @Getter
//    @Setter
//    @Id
//    @GeneratedValue(generator = "MODULE_OPTION_SRL_GEN", strategy = GenerationType.SEQUENCE)
//    @Column(name = "MODULE_OPTION_SRL")
//    private long moduleOptionIdx;

    @Getter
    @Setter
    @Id
    @Column(name = "MODULE_IDX", nullable = false)
    private String moduleIdx;

    @Getter
    @Setter
    @Id
    @Column(name = "OPTIONS_NAME", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "OPTIONS_VALUE")
    private String value;

    @Getter
    @Setter
    @Column(name = "OPTIONS_COMMENT")
    private String title;

//    @Getter
//    @Setter
//    @Column(name = "REG_DATE", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    // todo 등록일 및 생성일 필드명 개선
//    private Date regDate;

    public ModuleOptionEntity() {
    }

    public ModuleOptionEntity(String name, String value) {
        this.name = name;
        this.value = value;
    }

//    @PrePersist
//    public void prePersist() {
//        this.regDate = new Date();
//    }

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
