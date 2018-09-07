package org.ainframe.web.view;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.Random;

import org.ainframe.web.config.context.ConfigContext;
import org.ainframe.web.config.model.Config;
import org.ainframe.web.module.context.ModuleContext;
import org.ainframe.web.module.model.Module;
import org.apache.commons.lang3.StringUtils;
import org.jmock.lib.concurrent.Blitzer;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@ActiveProfiles("real")
public class ModuleViewResolverThreadSafeTest {
    private Blitzer blitzer;

    @Autowired
    private ModuleContext moduleContext;

    @Autowired
    private ConfigContext configContext;

    @Autowired
    private ModuleViewResolver mvr;

    @After
    public void after() {
        blitzer.shutdown();
    }

    @Test
    public void test() throws InterruptedException {
        blitzer = new Blitzer(200, 10);

        blitzer.blitz(new Runnable() {
            @Override
            public void run() {
                String[] modules = new String[]{ "board", "config", "code", "menu", "layout"};
                String[] text = new String[]{ "tpl", "ts", "aa", "ss", "ee"};
                String moduleId = modules[new Random().nextInt(4)];
                Config config = configContext.getConfig();
                Module module = moduleContext.getModule(moduleId);
                Module parentModule = Objects.equals(module.getModuleName(), module.getModuleId()) ?
                    module : moduleContext.getModule(module.getModuleName());
                ModuleViewRender mv = mvr.render(moduleId, "test.ftl");

                String title = text[new Random().nextInt(4)];
                String sk = text[new Random().nextInt(4)];
                String temp = text[new Random().nextInt(4)];

                mv.changeBrowserTitle(title);
                mv.changeSkin(sk);
                mv.changeTemplate(temp + ".ftl");

                mv.done();

                // 스킨 테스트
                String skin = module.isOnlyUseTheme() ?
                    StringUtils.defaultIfEmpty(config.getSkin(), config.getBasicSkin()) : sk;
                assertEquals(mv.getSkin(), skin);
                assertEquals(mv.getTemplate(), temp + ".ftl");

                // 브라우저 타이틀 테스트
                String browserTitle = config.getTitleOverwrite().isValue() ? title : config.getTitle();
                assertEquals(browserTitle, mv.getBrowserTitle());

                // 모듈 정보
                assertEquals(mv.getModule(), module);
                assertEquals(mv.getParentModule(), parentModule);
            }
        });
    }
}
