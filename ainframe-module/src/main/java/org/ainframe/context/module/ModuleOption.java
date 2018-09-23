package org.ainframe.context.module;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 29.
 */
@Data
@Setter(AccessLevel.NONE)
@Builder
public class ModuleOption implements Serializable {
    private String name;
    private String value;
    private String title;
}
