package org.ainframe.web.module.service;

import com.google.common.collect.Lists;
import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.model.ModuleSearch;
import org.ainframe.web.module.repository.ModuleEntityRepository;
import org.ainframe.web.module.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */
@Service
@Transactional
public class ViewModuleService {
  private ModuleEntityRepository moduleEntityRepository;

  @Autowired
  public void setModuleEntityRepository(ModuleEntityRepository moduleEntityRepository) {
    this.moduleEntityRepository = moduleEntityRepository;
  }

  /**
   * 검색 조건을 이용하여 모듈을 페이지 목록으로 구한다.
   * @param page 페이지 번호
   * @param size 목록 수
   * @param moduleSearch 검색 조건
   * @return Page
   */
  public Page<ModuleEntity> getModulePage(int page, int size, ModuleSearch moduleSearch) {
    switch (moduleSearch.getSearchType()) {
      case MODULE_ID:
        return moduleEntityRepository.findByModuleId(moduleSearch.getSearchValue(), new PageRequest(page, size));
      case MODULE_NAME:
        return moduleEntityRepository.findByModuleName(moduleSearch.getSearchValue(), new PageRequest(page, size));
      case BROWSER_TITLE:
        return moduleEntityRepository.findByBrowserTitleStartingWith(
          moduleSearch.getSearchValue(), new PageRequest(page, size));
      default:
        return moduleEntityRepository.findAll(new PageRequest(page, size));
    }
  }

  /**
   * 검색 조건이 없는 경우 모듈을 페이지 목록으로 구한다.
   * @param page 페이지 번호
   * @param size 목록 수
   * @return List
   */
  public List<ModuleEntity> getModulePageList(int page, int size) {
    return getModulePageList(page, size, new ModuleSearch(ModuleSearch.SearchType.NONE, null));
  }

  /**
   * 검색 조건이 있는 경우 모듈을 페이지 목록으로 구한다.
   * @param page 페이지 번호
   * @param size 목록 수
   * @param moduleSearch 검색 조건
   * @return List
   */
  public List<ModuleEntity> getModulePageList(int page, int size, ModuleSearch moduleSearch) {
    return Lists.newArrayList(this.getModulePage(page, size, moduleSearch).iterator());
  }

  public ModuleEntity getModule(String moduleIdx) {
    return this.moduleEntityRepository.findOne(moduleIdx);
  }

  public ModuleEntity getModuleByModuleId(String moduleId) {
    return this.moduleEntityRepository.getOneByModuleId(moduleId);
  }

  public ModuleEntity save(ModuleEntity moduleEntity) { return this.moduleEntityRepository.save(moduleEntity); }

  public void delete(String moduleIdx) {
    this.moduleEntityRepository.delete(moduleIdx);
  }
}
