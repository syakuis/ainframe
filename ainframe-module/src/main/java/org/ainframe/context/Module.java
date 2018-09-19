package org.ainframe.context;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 29.
 */
@Builder
@Getter
@EqualsAndHashCode
public class Module implements Serializable {
    private String moduleIdx;
    private String moduleId;
    private String moduleName;
    private String skin;
    private boolean onlyUseTheme;
    private String browserTitle;
    private Map<String, ModuleOption> moduleOptions;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
