package org.ainframe.web.config.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 */
@Data
@Setter(AccessLevel.NONE)
@Builder
public class Config implements Serializable {
    private String browserTitle;
    private boolean browserTitleOverwrite;
    private String indexUrl;
    private String layoutIdx;
    private String basicSkin;
    private String skin;
    private String styleTheme;
}
