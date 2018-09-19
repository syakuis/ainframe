package org.ainframe.web.config.model;

import org.ainframe.context.Config;
import org.ainframe.core.data.enums.YesOrNo;

import lombok.Data;
import lombok.ToString;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 */
@Data
@ToString
public class ConfigDetails implements Config {
    private String moduleIdx;
    private String title;
    private YesOrNo titleOverwrite;
    private String indexPage;
    private String layoutIdx;
    private String basicSkin;
    private String skin;
    private String styleTheme;
}
