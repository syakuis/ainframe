package org.ainframe.web.module.controller;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */

import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.model.ModuleSearch;
import org.ainframe.web.module.service.ViewModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/module")
public class ApiAdminModuleController {
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

  @GetMapping("/{id}")
  public ModuleEntity getModule(@PathVariable("id") String moduleIdx) {
    return viewModuleService.getModule(moduleIdx);
  }

  @PostMapping()
  public ModuleEntity save(@RequestBody ModuleEntity moduleEntity) {
    return viewModuleService.save(moduleEntity);
  }

  @DeleteMapping("/{id")
  public void delete(@PathVariable("id") String moduleIdx) {
    viewModuleService.delete(moduleIdx);
  }
}
