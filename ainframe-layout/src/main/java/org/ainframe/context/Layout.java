package org.ainframe.context;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
@Builder
@Data
@Setter(AccessLevel.NONE)
public class Layout implements Serializable {
    private String layoutIdx;
    private String menuIdx;
    private String layoutName;
}
