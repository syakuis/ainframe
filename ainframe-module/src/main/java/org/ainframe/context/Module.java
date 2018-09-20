package org.ainframe.context;

import java.io.Serializable;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 29.
 */
@Builder
@Data
@Setter(AccessLevel.NONE)
public class Module implements Serializable {
    private String moduleIdx;
    private String moduleId;
    private String moduleName;
    private String skin;
    /**
     * Config 의 테마를 우선 사용할지 여부
     */
    private boolean onlyUseTheme;
    private String browserTitle;
    private Map<String, ModuleOption> moduleOptions;
}
