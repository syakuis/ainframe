package org.ainframe.context;

import org.ainframe.web.menu.model.Menu;

import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
public interface MenuContextService {
    /**
     * 모든 메뉴 정보를 가져와 menuIdx, menuName 만으로 만들어 반환한다.
     * @return Map
     */
    Map<String, String> getAllMenuName();

    /**
     * menuIdx 에 해당하는 메뉴정보를 가져와 메뉴 항목 전체를 트리와 목록 구조를 반환한다.
     * @param menuIdx 메뉴 번호
     * @return Menu
     */
    Menu getMenu(String menuIdx);
}
