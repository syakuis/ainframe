package org.ainframe.web.module.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.domain.ModuleOptionEntity;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 29.
 */
@Builder
@Getter
public class Module implements Serializable {
    private String moduleIdx;
    private String moduleId;
    private String moduleName;
    private String skin;
    private boolean onlyUseTheme;
    private String browserTitle;
    private Map<String, ModuleOption> moduleOptions;

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
}
