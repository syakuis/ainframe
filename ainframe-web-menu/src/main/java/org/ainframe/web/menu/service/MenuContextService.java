package org.ainframe.web.menu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 13.
 */
@Service
public class MenuContextService {
    private CacheMenuService menuService;

    @Autowired
    public void setMenuService(CacheMenuService menuService) {
        this.menuService = menuService;
    }
}
