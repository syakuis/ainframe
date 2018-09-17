package org.ainframe.context;

import org.ainframe.context.model.MenuDetails;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
public interface MenuContextService {
    MenuDetails getMenuDetails(String menuIdx);
}
