package org.ainframe.web.view;

import lombok.extern.slf4j.Slf4j;
import org.ainframe.context.ConfigContext;
import org.ainframe.context.Layout;
import org.ainframe.context.LayoutContext;
import org.ainframe.context.MenuContext;
import org.ainframe.context.module.Module;
import org.ainframe.context.module.ModuleContext;
import org.ainframe.web.config.WebProperties;
import org.ainframe.web.config.model.Config;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class WebViewResolverTest {

    @Autowired
    private WebProperties webProperties;

    @Autowired
    private WebViewResolver webViewResolver;

    @Autowired
    private ConfigContext configContext;

    @Autowired
    private ModuleContext moduleContext;

    @Autowired
    private LayoutContext layoutContext;

    @Autowired
    private MenuContext menuContext;

    private Config config;
    private Module module;
    private WebViewRender webView;

    private String moduleId = "board";
    private String template = "board.list.ftl";
    private String layoutIdx;

    @Before
    public void setup() {
        this.config = configContext.getConfig();
        this.module = moduleContext.getModule(moduleId);
        this.webView = webViewResolver.render(moduleId, template);
        this.layoutIdx = this.getDefaultLayoutIdx(template);
    }

    @Test
    public void initTest() {
        assertNotNull(this.config);
        assertNotNull(this.module);
        assertNotNull(this.webView);
    }

    @Test
    public void browserTitleTest() {
        // 초기 타이틀 검증
        assertEquals(webView.getBrowserTitle(),
            config.isBrowserTitleOverwrite() ? module.getBrowserTitle() : config.getBrowserTitle());

        // 타이틀 변경 검증
        webView.changeBrowserTitle("테스트");
        assertEquals(webView.getBrowserTitle(),
            config.isBrowserTitleOverwrite() ? "테스트" : module.getBrowserTitle());

        // 타이틀 강제 변경 검증
        webView.changeBrowserTitle("강제변경", true);
        assertEquals(webView.getBrowserTitle(), "강제변경");
    }

    @Test
    public void moduleTest() {
        assertEquals(this.webView.getModulePath(), WebViewUtils.getModulePath(this.module.getModuleName(), moduleId));
        assertSame(this.webView.getModule(), this.module);
        assertSame(this.webView.getParentModule(), this.moduleContext.getModule(this.module.getModuleName()));
    }

    private String getDefaultSkin(String template) {
        if (WebViewUtils.isAdminTemplate(template)) {
            return webProperties.getAdminSkin();
        }

        if (this.module.isOnlyUseTheme()) {
            return StringUtils.defaultIfEmpty(config.getSkin(), config.getBasicSkin());
        }

        return StringUtils.defaultIfEmpty(this.module.getSkin(), this.webProperties.getSkin());
    }

    @Test
    public void defaultSkinAndTemplateTest() {
        String skin = getDefaultSkin(this.template);
        String templatePath = ModuleViewRender.createTemplatePath(this.module.getModuleName(), skin);
        String templateFile = ModuleViewRender.createTemplateFile(templatePath, this.template);
        if (!ModuleViewRender.templateFileExists(templateFile)) {
            templatePath = ModuleViewRender.createTemplatePath(this.module.getModuleName(), this.config.getBasicSkin());
            templateFile = ModuleViewRender.createTemplateFile(templatePath, this.template);
        }

        assertEquals(this.webView.getTemplatePath(), templatePath);
        assertEquals(this.webView.getTemplateFile(), templateFile);
    }

    @Test
    public void skinChangeTest() {
        String skin = "abc";
        this.webView.changeSkin(skin);
        String templatePath = ModuleViewRender.createTemplatePath(this.module.getModuleName(), skin);
        String templateFile = ModuleViewRender.createTemplateFile(templatePath, this.template);

        assertEquals(this.webView.getTemplatePath(), templatePath);
        assertEquals(this.webView.getTemplateFile(), templateFile);
    }

    @Test
    public void templateChangeTest() {
        String template = "admin.list.ftl";
        this.webView.changeTemplate(template);
        String templatePath = ModuleViewRender.createTemplatePath(this.module.getModuleName(), this.module.getSkin());
        String templateFile = ModuleViewRender.createTemplateFile(templatePath, template);

        assertEquals(this.webView.getTemplatePath(), templatePath);
        assertEquals(this.webView.getTemplateFile(), templateFile);
    }

    /**
     * @see ModuleViewRender#getDefaultLayoutIdx(String)
     */
    private String getDefaultLayoutIdx(String template) {
        // 관리자 여부에 따라 기본 스킨과 레이아웃이 변경됨.
        if (WebViewUtils.isAdminTemplate(template)) {
            return this.webProperties.getAdminLayoutIdx();
        } else if (this.module.isOnlyUseTheme()) {
            return this.config.getLayoutIdx();
        }

        return StringUtils.defaultIfEmpty(this.module.getLayoutIdx(), this.config.getLayoutIdx());
    }

    @Test
    public void defaultLayoutAndMenuTest() {
        log.debug("layoutIdx : {}", layoutIdx);

        if (layoutIdx != null) {
            Layout layout = layoutContext.getLayout(layoutIdx);
            assertNotNull(layout);
            assertSame(this.webView.getLayout(), layout);
            assertEquals(this.webView.getLayout(), layout);

            log.debug("menuIdx : {}", layout.getMenuIdx());
            if (layout.getMenuIdx() != null) {
                assertNotNull(this.webView.getMenu());
                assertFalse(this.webView.getMenu().getMenuNodes().isEmpty());
                assertSame(this.webView.getMenu(), menuContext.getMenu(layout.getMenuIdx()));
                assertEquals(this.webView.getMenu(), menuContext.getMenu(layout.getMenuIdx()));
            }
        } else {
            log.warn("layoutIdx is empty.");
        }
    }

    @Test
    public void layoutChangeTest() {
        this.webView.changeLayout("ccc");
        String layoutPath = LayoutViewRender.createLayoutPath("ccc");
        assertEquals(this.webView.getLayoutPath(), layoutPath);
        this.webView.changeLayoutTemplate("test.ftl");
        String layoutFile = LayoutViewRender.createLayoutFile(layoutPath, "test.ftl");
        assertEquals(this.webView.getLayoutFile(), layoutFile);
    }
}
