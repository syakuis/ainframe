package org.ainframe.web.menu.service;

import javax.transaction.Transactional;

import org.ainframe.web.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
@Service
@Transactional
public class MenuService {
    private MenuRepository menuRepository;

    @Autowired
    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
}
