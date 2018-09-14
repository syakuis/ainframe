package org.ainframe.web.module.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.ainframe.core.data.enums.YesOrNo;
import org.ainframe.context.model.Module;
import org.ainframe.context.model.ModuleOption;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * todo 테이블 필드명 새롭게 정리하기.
 * todo regDate 대신 정렬할 수 있는 필드로 변경한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 24.
 */
@Entity
@Table(name = "MODULE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuleEntity implements Serializable {
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
        strategy = "org.ainframe.data.jpa.StringSequenceIdentifier",
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

    @Column(name = "MODULE_ID", nullable = false, unique = true)
    private String moduleId;

    @Column(name = "MODULE_NAME", nullable = false)
    private String moduleName;

    @Column(name = "BROWSER_TITLE")
    private String browserTitle;

    @Column(name = "SKIN")
    private String skin;

    @Column(name = "LAYOUT_IDX")
    private String layoutIdx;

    // todo YN 로 변경하고 EnumType.STRING 설정한다.
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "USE_THEME")
    private YesOrNo onlyUseTheme;

    @Column(name = "REG_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    // todo 등록일 및 생성일 필드명 개선
    private Date regDate;

    @OneToMany(targetEntity = ModuleOptionEntity.class)
    @JoinColumn(name = "MODULE_IDX")
    private List<ModuleOptionEntity> moduleOptionEntities;

    @PrePersist
    public void prePersist() {
        this.regDate = new Date();
    }

    public static List<ModuleOptionEntity> createModuleOptionEntities(ModuleOptionEntity ...moduleOptionEntity) {
        return Lists.newArrayList(moduleOptionEntity);
    }

    /**
     * {@link ModuleEntity} 를 Module 로 변경한다.
     * @param moduleEntity {@link ModuleEntity}
     * @return Module
     */
    public static Module transform(ModuleEntity moduleEntity) {
        Map<String, ModuleOption> moduleOptions = Maps.transformValues(
            Maps.uniqueIndex(
                moduleEntity.getModuleOptionEntities(),
                new Function<ModuleOptionEntity, String>() {
                    @Override
                    public String apply(ModuleOptionEntity input) {
                        return input.getName();
                    }
                }),
            new Function<ModuleOptionEntity, ModuleOption>() {
                @Override
                public ModuleOption apply(ModuleOptionEntity entity) {
                    return ModuleOption.builder()
                        .name(entity.getName())
                        .value(entity.getValue())
                        .title(entity.getTitle()).build();
                }
            });

        return Module.builder()
            .moduleIdx(moduleEntity.getModuleIdx())
            .moduleId(moduleEntity.getModuleId())
            .moduleName(moduleEntity.getModuleName())
            .skin(moduleEntity.getSkin())
            .onlyUseTheme(moduleEntity.getOnlyUseTheme().isValue())
            .browserTitle(moduleEntity.getBrowserTitle())
            .moduleOptions(moduleOptions).build();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
