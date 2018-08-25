package org.ainframe.web.module.domain;

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
@SequenceGenerator(
    name = "MODULE_IDX_SEQ",
    sequenceName = "MODULE_IDX_SEQ",
    allocationSize = 1
)
public class ModuleEntity {

    @Getter
    @Setter
    @Id
    @GenericGenerator(
        name = "assigned-sequence",
        strategy = "org.ainframe.web.module.domain.StringSequenceIdentifier",
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "sequence_name", value = "MODULE_IDX_SEQ"),
            @org.hibernate.annotations.Parameter(
                name = "sequence_prefix", value = "MODU"),
        }
    )
    @GeneratedValue(
        generator = "assigned-sequence",
        strategy = GenerationType.SEQUENCE)
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

    public ModuleEntity() {
    }

    public ModuleEntity(String moduleId, String moduleName) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
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
