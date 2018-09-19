package org.ainframe.web.module;

import javax.transaction.Transactional;

import org.ainframe.web.module.service.ModuleService;
import org.ainframe.web.module.service.ViewModuleService;
import org.ainframe.web.module.service.model.ModuleSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("real")
@EnableAutoConfiguration
@Transactional
public class ModuleServiceRealTest {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ViewModuleService viewModuleService;

    @Test
    public void 페이지_목록() {
        viewModuleService.getModulePageList(1, 10);
        viewModuleService.getModulePageList(
            1, 10, new ModuleSearch(ModuleSearch.SearchType.MODULE_ID, "module"));
        viewModuleService.getModulePageList(
            1, 10, new ModuleSearch(ModuleSearch.SearchType.MODULE_NAME, "board"));
        viewModuleService.getModulePageList(
            1, 10, new ModuleSearch(ModuleSearch.SearchType.BROWSER_TITLE, "공지"));
    }

}
