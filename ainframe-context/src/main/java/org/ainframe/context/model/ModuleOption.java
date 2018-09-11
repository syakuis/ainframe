package org.ainframe.context.model;

import java.io.Serializable;

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
public class ModuleOption implements Serializable {
    private String name;
    private String value;
    private String title;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
