package org.ainframe.web.module.controller;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */

import org.ainframe.core.util.PageNavigator;
import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.service.ViewModuleService;
import org.ainframe.web.module.service.model.ModuleSearch;
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
    private ViewModuleService viewModuleService;

    @Autowired
    public void setViewModuleService(ViewModuleService viewModuleService) {
        this.viewModuleService = viewModuleService;
    }

    @GetMapping("")
    public ModelAndView moduleList(
        @RequestParam(defaultValue = "1", required = false) int page,
        @RequestParam(value = "searchType", required = false) String searchType,
        @RequestParam(value = "searchValue", required = false) String searchValue
    ) {
        ModuleSearch moduleSearch = searchType != null ?
            new ModuleSearch(ModuleSearch.SearchType.valueOf(searchType), searchValue) :
            new ModuleSearch();

        Page<ModuleEntity> pages = viewModuleService.getModulePage(
            page, 10, moduleSearch);

        ModelAndView mav = new ModelAndView("");
        mav.addObject("listModule", pages.iterator());
        mav.addObject("moduleSearch", moduleSearch);
        mav.addObject("NAVI", PageNavigator.computed(page, pages.getTotalElements()));

        return mav;
    }
}
