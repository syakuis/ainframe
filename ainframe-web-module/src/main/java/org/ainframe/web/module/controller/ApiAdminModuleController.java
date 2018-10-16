package org.ainframe.web.module.controller;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */

import org.ainframe.web.module.config.ModuleProperties;
import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.model.ModuleSearch;
import org.ainframe.web.module.service.ViewModuleService;
import org.ainframe.webmvc.view.WebViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/module")
public class ApiModuleController {
  @Autowired
  private ModuleProperties moduleProperties;
  @Autowired
  private WebViewResolver webViewResolver;
  @Autowired
  private ViewModuleService viewModuleService;

  @GetMapping
  public Page<ModuleEntity> getModulePageList(
    @RequestParam(defaultValue = "1", required = false) int page,
    @RequestParam(value = "searchType", required = false) String searchType,
    @RequestParam(value = "searchValue", required = false) String searchValue
  ) {

    ModuleSearch moduleSearch = searchType != null ?
      new ModuleSearch(ModuleSearch.SearchType.valueOf(searchType), searchValue) :
      new ModuleSearch();

    return viewModuleService.getModulePage(
      page, 10, moduleSearch);
  }

  // todo 모듈 수정

  // todo 모듈 삭제
}
