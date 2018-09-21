package org.ainframe.context;

import org.ainframe.web.menu.model.Menu;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
public interface MenuContextService {
    Menu getMenu(String menuIdx);
}
