package org.ainframe.context;

import java.util.List;

import org.ainframe.context.model.Menu;
import org.ainframe.context.model.MenuDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
//@Component
public class MenuContext {
    private MenuContextService menuContextService;

//    @Autowired
    public void setMenuContextService(MenuContextService menuContextService) {
        this.menuContextService = menuContextService;
    }

    /**
     * 현재 요청한 경로(Request path)에 맞는 메뉴를 찾아 반환한다.
     */
    public Menu getCurrentMenu(String menuIdx, String path) {
        MenuDetails menuDetails = menuContextService.getMenuDetails(menuIdx);
        List<Menu> menus =  menuDetails.getMenus();

        return Iterables.find(menus, new Predicate<Menu>() {
            @Override
            public boolean apply(Menu input) {
                return false;
            }
        });
    }
}
