package org.ainframe.web.view;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.ainframe.context.Layout;
import org.ainframe.context.LayoutContext;
import org.ainframe.context.MenuContext;
import org.ainframe.web.menu.model.Menu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * todo https://gitlab.com/syakuis/ainframe/issues/16
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 27.
 */
@Slf4j
public class LayoutViewRender implements LayoutView {
    private final String LAYOUT_TEMPLATE = "layout.ftl";
    @Getter
    private final Layout layout;
    @Getter
    private final Menu menu;

    private String layoutName;
    /**
     * 레이아웃 템플릿 기본적으로 설정한다.
     */
    private String layoutTemplate;
    /**
     * 레이아웃 템플릿 상대 경로를 설정한다.
     */
    @Getter
    private String layoutPath;
    /**
     * layoutPath 포함한 템플릿 파일 경로를 설정한다.
     */
    @Getter
    private String layoutFile;

    public LayoutViewRender(LayoutContext layoutContext, MenuContext menuContext, String layoutIdx) {
        Assert.notNull(layoutIdx, "layoutIdx must not be null.");

        this.layout = layoutContext.getLayout(layoutIdx);

        Menu menu = null;

        if (this.layout != null) {
            this.layoutName = this.layout.getLayoutName();
            if (this.layout.getMenuIdx() != null) {
                menu = menuContext.getMenu(this.layout.getMenuIdx());
            }
            this.changeLayoutAndTemplate(this.layout.getLayoutName(),
                StringUtils.defaultIfEmpty(this.layout.getLayoutTemplate(), LAYOUT_TEMPLATE));
        }

        this.menu = menu;
    }

    @Override
    public void changeLayout(String layout) {
        this.changeLayoutAndTemplate(layout, this.layoutTemplate);
    }

    @Override
    public void changeLayoutTemplate(String layoutTemplate) {
        this.changeLayoutAndTemplate(this.layoutName, layoutTemplate);
    }

    @Override
    public void changeLayoutAndTemplate(String layoutName, String layoutTemplate) {
        this.layoutPath = createLayoutPath(layoutName);
        this.layoutFile = createLayoutFile(layoutPath, layoutTemplate);
        this.layoutTemplate = layoutTemplate;
        this.layoutName = layoutName;
    }

    public static String createLayoutPath(String layoutName) {
        if (StringUtils.isEmpty(layoutName)) {
            throw new IllegalArgumentException("layoutName must not be null.");
        }

        return "/layouts/" + layoutName;
    }

    public static String createLayoutFile(String layoutPath, String layoutTemplate) {
        if (StringUtils.isEmpty(layoutTemplate)) {
            throw new IllegalArgumentException("layoutTemplate must not be null.");
        }
        return layoutPath + "/" + layoutTemplate;
    }
}
