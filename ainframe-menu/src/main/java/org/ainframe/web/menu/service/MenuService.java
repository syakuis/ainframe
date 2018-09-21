package org.ainframe.web.menu.service;

import org.ainframe.web.menu.domain.MenuTreeEntity;
import org.ainframe.web.menu.repository.MenuTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
@Service
@Transactional
public class MenuService {
    private MenuTreeRepository menuTreeRepository;

    @Autowired
    public void setMenuTreeRepository(MenuTreeRepository menuTreeRepository) {
        this.menuTreeRepository = menuTreeRepository;
    }

    public List<MenuTreeEntity> getMenus() {
        return menuTreeRepository.findAll();
    }

    public MenuTreeEntity getMenuTreeByMenuIdx(String menuIdx) {
        return menuTreeRepository.findOne(menuIdx);
    }
}
