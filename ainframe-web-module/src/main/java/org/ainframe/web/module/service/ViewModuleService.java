package org.ainframe.web.module.service;

import java.util.List;

import javax.transaction.Transactional;

import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.repository.ModuleRepository;
import org.ainframe.web.module.service.model.ModuleSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */
@Service
@Transactional
public class ViewModuleService {
    private ModuleRepository moduleRepository;

    @Autowired
    public void setModuleRepository(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public Page<ModuleEntity> getModulePage(int page, int size, ModuleSearch moduleSearch) {
        switch (moduleSearch.getSearchType()) {
            case MODULE_ID:
                return moduleRepository.findByModuleId(moduleSearch.getSearchValue(), new PageRequest(page, size));
            case MODULE_NAME:
                return moduleRepository.findByModuleName(moduleSearch.getSearchValue(), new PageRequest(page, size));
            case BROWSER_TITLE:
                return moduleRepository.findByBrowserTitleStartingWith(
                    moduleSearch.getSearchValue(), new PageRequest(page, size));
            default:
                return moduleRepository.findAll(new PageRequest(page, size));
        }
    }

    public List<ModuleEntity> getModulePageList(int page, int size) {
        return getModulePageList(page, size, new ModuleSearch(ModuleSearch.SearchType.NONE, null));
    }

    public List<ModuleEntity> getModulePageList(int page, int size, ModuleSearch moduleSearch) {
        return Lists.newArrayList(this.getModulePage(page, size, moduleSearch).iterator());
    }
}
