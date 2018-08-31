package org.ainframe.web.module.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 29.
 */
@Builder
public class ModuleOption implements Serializable {
    @Getter
    private String name;
    @Getter
    private String value;
    @Getter
    private String title;
}
