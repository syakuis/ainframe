package org.ainframe.web.module.controller;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */

import org.ainframe.core.util.PageNavigator;
import org.ainframe.web.module.config.ModuleProperties;
import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.model.ModuleSearch;
import org.ainframe.web.module.service.ViewModuleService;
import org.ainframe.webmvc.view.WebViewRender;
import org.ainframe.webmvc.view.WebViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin/module")
public class AdminModuleController {
  @Autowired
  private ModuleProperties moduleProperties;
  @Autowired
  private WebViewResolver webViewResolver;
  @Autowired
  private ViewModuleService viewModuleService;

  @GetMapping
  public ModelAndView getModulePageList(
    @RequestParam(defaultValue = "1", required = false) int page,
    @RequestParam(value = "searchType", required = false) String searchType,
    @RequestParam(value = "searchValue", required = false) String searchValue
  ) {
    WebViewRender wvr = webViewResolver.render(moduleProperties.getModuleName(), "admin.module.list.ftl");

    ModuleSearch moduleSearch = searchType != null ?
      new ModuleSearch(ModuleSearch.SearchType.valueOf(searchType), searchValue) :
      new ModuleSearch();

    Page<ModuleEntity> pages = viewModuleService.getModulePage(
      page, 10, moduleSearch);

    wvr.addObject("listModule", pages.iterator());
    wvr.addObject("moduleSearch", moduleSearch);
    wvr.addObject("NAVI", PageNavigator.computed(page, pages.getTotalElements()));

    return wvr.done();
  }

  // todo 모듈 수정

  // todo 모듈 삭제
}
