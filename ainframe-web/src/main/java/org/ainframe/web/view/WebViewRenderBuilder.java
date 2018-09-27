package org.ainframe.web.view;

import org.ainframe.context.ConfigContext;
import org.ainframe.context.LayoutContext;
import org.ainframe.context.MenuContext;
import org.ainframe.context.module.ModuleContext;
import org.ainframe.web.config.WebProperties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 27.
 */
public class WebViewRenderBuilder {
    private WebProperties webProperties;
    private ModuleContext moduleContext;
    private LayoutContext layoutContext;
    private MenuContext menuContext;
    private ConfigContext configContext;
    private String moduleId;
    private String template;

    public static WebViewRenderBuilder builder() {
        return new WebViewRenderBuilder();
    }

    public WebViewRenderBuilder webProperties(WebProperties webProperties) {
        this.webProperties = webProperties;
        return this;
    }

    public WebViewRenderBuilder moduleContext(ModuleContext moduleContext) {
        this.moduleContext = moduleContext;
        return this;
    }

    public WebViewRenderBuilder layoutContext(LayoutContext layoutContext) {
        this.layoutContext = layoutContext;
        return this;
    }

    public WebViewRenderBuilder menuContext(MenuContext menuContext) {
        this.menuContext = menuContext;
        return this;
    }

    public WebViewRenderBuilder configContext(ConfigContext configContext) {
        this.configContext = configContext;
        return this;
    }

    public WebViewRenderBuilder moduleId(String moduleId) {
        this.moduleId = moduleId;
        return this;
    }

    public WebViewRenderBuilder template(String template) {
        this.template = template;
        return this;
    }

    public WebViewRender build() {
        return new WebViewRender(
            webProperties, moduleContext, layoutContext, menuContext, configContext, moduleId, template);
    }
}
