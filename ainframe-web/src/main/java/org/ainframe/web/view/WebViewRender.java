package org.ainframe.web.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.ainframe.context.ConfigContext;
import org.ainframe.context.Layout;
import org.ainframe.context.LayoutContext;
import org.ainframe.context.MenuContext;
import org.ainframe.context.module.Module;
import org.ainframe.context.module.ModuleContext;
import org.ainframe.web.config.WebProperties;
import org.ainframe.web.menu.model.Menu;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 24.
 */
@Slf4j
public class WebViewRender implements ModuleView, LayoutView {
    private final ModuleView moduleView;
    private final LayoutView layoutView;

    private final ModelAndView modelAndView;

    @Getter @Setter
    private boolean onlyUseModule;
    @Getter @Setter
    private boolean disableLayout;
    @Getter @Setter
    private boolean disableMenu;

    public WebViewRender(
        WebProperties webProperties, ModuleContext moduleContext,
        LayoutContext layoutContext, MenuContext menuContext, ConfigContext configContext, String moduleId, String template) {

        ModuleViewRender moduleViewRender = new ModuleViewRender(webProperties, moduleContext, configContext, moduleId);
        moduleViewRender.render(template);

        LayoutViewRender layoutViewRender = new LayoutViewRender(
            layoutContext, menuContext, moduleViewRender.getDefaultLayoutIdx(template));

        this.moduleView = moduleViewRender;
        this.layoutView = layoutViewRender;
        this.modelAndView = new ModelAndView(webProperties.getIndexTemplate());
    }

    /**
     * {@link ModelAndView#addObject(Object)}
     * @param attributeName String key
     * @param attributeValue Object value
     */
    public void addObject(String attributeName, Object attributeValue) {
        this.modelAndView.addObject(attributeName, attributeValue);
    }

    @Override
    public void changeBrowserTitle(String browserTitle) {
        this.moduleView.changeBrowserTitle(browserTitle);
    }

    @Override
    public void changeBrowserTitle(String browserTitle, boolean must) {
        this.moduleView.changeBrowserTitle(browserTitle, must);
    }

    @Override
    public void changeTemplate(String template) {
        this.moduleView.changeTemplate(template);
    }

    @Override
    public void changeSkin(String skin) {
        this.moduleView.changeSkin(skin);
    }

    @Override
    public void changeSkinAndTemplate(String skin, String template) {
        this.moduleView.changeSkinAndTemplate(skin, template);
    }

    @Override
    public Module getModule() {
        return this.moduleView.getModule();
    }

    @Override
    public Module getParentModule() {
        return this.moduleView.getParentModule();
    }

    @Override
    public String getModulePath() {
        return this.moduleView.getModulePath();
    }

    @Override
    public String getBrowserTitle() {
        return this.moduleView.getBrowserTitle();
    }

    @Override
    public String getTemplatePath() {
        return this.moduleView.getTemplatePath();
    }

    @Override
    public String getTemplateFile() {
        return this.moduleView.getTemplateFile();
    }

    @Override
    public void changeLayout(String layout) {
        this.layoutView.changeLayout(layout);
    }

    @Override
    public void changeLayoutTemplate(String layoutTemplate) {
        this.layoutView.changeLayoutTemplate(layoutTemplate);
    }

    @Override
    public void changeLayoutAndTemplate(String layoutName, String layoutTemplate) {
        this.layoutView.changeLayoutAndTemplate(layoutName, layoutTemplate);
    }

    @Override
    public Layout getLayout() {
        return this.layoutView.getLayout();
    }

    @Override
    public Menu getMenu() {
        return this.layoutView.getMenu();
    }

    @Override
    public String getLayoutPath() {
        return this.layoutView.getLayoutPath();
    }

    @Override
    public String getLayoutFile() {
        return this.layoutView.getLayoutFile();
    }

    public ModelAndView done() {
        Map<String, Object> data = new HashMap<>();
        data.put("module", this.getModule());
        data.put("parentModule", this.getParentModule());
        data.put("modulePath", this.getModulePath());
        data.put("templateFile", this.getTemplateFile());
        data.put("browserTitle", this.getBrowserTitle());
        data.put("layoutPath", this.getLayoutPath());
        data.put("layoutFile", this.getLayoutFile());
        data.put("layout", this.getLayout());
        data.put("menu", this.getMenu());
        data.put("onlyUseModule", onlyUseModule);
        data.put("disableLayout", disableLayout);
        data.put("disableMenu", disableMenu);
        this.modelAndView.addObject("MV", data);
        return this.modelAndView;
    }

    @Override
    public String toString() {
        Module module = this.getModule();
        return new StringBuilder("\r\n{> ModuleViewResolver <}===============================================\r\n")
                    .append("> modulePath : ").append(this.getModulePath()).append("\r\n")
                    .append("> layoutPath : ").append(this.getLayoutPath()).append("\r\n")
                    .append("> layoutFile : ").append(this.getLayoutFile()).append("\r\n")
                    .append("> templateFile : ").append(this.getTemplateFile()).append("\r\n")
                    .append("> browserTitle : ").append(this.getBrowserTitle()).append("\r\n")
                    .append("> onlyUseModule : ").append(this.onlyUseModule).append("\r\n")
                    .append("> disableLayout : ").append(this.disableLayout).append("\r\n")
                    .append("> disableMenu : ").append(this.disableMenu).append("\r\n")
                    .append("==========================================================================\r\n")
                    .toString();
    }
}
