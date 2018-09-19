package org.ainframe.web.view;

import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.ainframe.context.ConfigContext;
import org.ainframe.context.ModuleContext;
import org.ainframe.context.Config;
import org.ainframe.context.Module;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("real")
public class ModuleViewResolverTest {
    @Autowired
    private ModuleContext moduleContext;

    @Autowired
    private ConfigContext configContext;

    @Autowired
    private ModuleViewResolver mvr;

    @Test
    public void 일반적인테스트() {
        Config config = configContext.getConfig();
        Module module = moduleContext.getModule("board");
        Module parentModule = Objects.equals(module.getModuleName(), module.getModuleId()) ?
            module : moduleContext.getModule(module.getModuleName());
        ModuleViewRender mv = mvr.render("board", "test.ftl");

        mv.addObject("test", "goodman");
        ModelAndView mav = mv.done();
        mv.addObject("test2", "goodman2");

        // 스킨 테스트
        String skin = module.isOnlyUseTheme() ?
            StringUtils.defaultIfEmpty(config.getSkin(), config.getBasicSkin()) : module.getSkin();
        assertEquals(mv.getSkin(), skin);
        assertEquals(mv.getTemplate(), "test.ftl");

        // 브라우저 타이틀 테스트
        String browserTitle = config.getTitleOverwrite().isValue() ? module.getBrowserTitle() : config.getTitle();
        assertEquals(browserTitle, mv.getBrowserTitle());

        // 모듈 정보
        assertEquals(mv.getModule(), module);
        assertEquals(mv.getParentModule(), parentModule);

        ModuleView moduleView = (ModuleView) mav.getModel().get("MV");

        assertEquals(mv.getModule(), moduleView.getModule());
        assertEquals(mv.getSkin(), moduleView.getSkin());

        assertEquals(mv.getModelAndView().getModel(), mav.getModel());
    }

    @Test
    public void 변경테스트() {
        Config config = configContext.getConfig();
        Module module = moduleContext.getModule("board");
        Module parentModule = Objects.equals(module.getModuleName(), module.getModuleId()) ?
            module : moduleContext.getModule(module.getModuleName());
        ModuleViewRender mv = mvr.render("board", "test.ftl");

        mv.changeBrowserTitle("good");
        mv.changeSkin("tpl");
        mv.changeTemplate("test2.ftl");

        mv.done();

        // 스킨 테스트
        String skin = module.isOnlyUseTheme() ?
            StringUtils.defaultIfEmpty(config.getSkin(), config.getBasicSkin()) : "tpl";
        assertEquals(mv.getSkin(), skin);
        assertEquals(mv.getTemplate(), "test2.ftl");

        // 브라우저 타이틀 테스트
        String browserTitle = config.getTitleOverwrite().isValue() ? "good" : config.getTitle();
        assertEquals(browserTitle, mv.getBrowserTitle());

        // 모듈 정보
        assertEquals(mv.getModule(), module);
        assertEquals(mv.getParentModule(), parentModule);
    }
}
