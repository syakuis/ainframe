package org.ainframe.web.module.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 24.
 */
@Entity
@Table(name = "MODULE")
public class ModuleEntity {

    @Getter
    @Setter
    @SequenceGenerator(
        name = "MODULE_IDX_GEN",
        sequenceName = "MODULE_IDX_SEQ",
        allocationSize = 1
    )
    @GeneratedValue(
        generator = "MODULE_IDX_GEN",
        strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
        name = "MODULE_IDX_GEN",
        strategy = "org.ainframe.web.module.domain.StringSequenceIdentifier",
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "sequence_name", value = "MODULE_IDX_SEQ"),
            @org.hibernate.annotations.Parameter(
                name = "sequence_prefix", value = "MODU"),
        }
    )
    @Id
    @Column(name = "MODULE_IDX", nullable = false, length = 20)
    private String moduleIdx;

    @Getter
    @Setter
    @Column(name = "MODULE_ID", nullable = false, unique = true)
    private String moduleId;

    @Getter
    @Setter
    @Column(name = "MODULE_NAME", nullable = false)
    private String moduleName;

    @Getter
    @Setter
    @Column(name = "BROWSER_TITLE")
    private String browserTitle;

    @Getter
    @Setter
    @Column(name = "SKIN")
    private String skin;

    @Getter
    @Setter
    @Column(name = "LAYOUT_IDX")
    private String layoutIdx;

    @Getter
    @Setter
    @Column(name = "REG_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    // todo 등록일 및 생성일 필드명 개선
    private Date regDate;

    @Getter
    @Setter
    @OneToMany(targetEntity = ModuleOptionEntity.class)
    @JoinColumn(name = "MODULE_IDX")
    private List<ModuleOptionEntity> moduleOptionEntities;

    public ModuleEntity() {
    }

    public ModuleEntity(String moduleId, String moduleName) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
    }

    @PrePersist
    public void prePersist() {
        this.regDate = new Date();
    }

    public static List<ModuleOptionEntity> createModuleOptionEntities(ModuleOptionEntity ...moduleOptionEntity) {
        return Arrays.asList(moduleOptionEntity);
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
