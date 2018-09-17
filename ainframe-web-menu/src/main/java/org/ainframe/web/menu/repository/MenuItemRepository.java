package org.ainframe.web.menu.repository;

import org.ainframe.web.menu.domain.MenuItemEntity;
import org.springframework.data.repository.Repository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 17.
 */
public interface MenuItemRepository extends Repository<MenuItemEntity, String> {
    MenuItemEntity findOneByMenuIdxAndTreeId(String menuIdx, String treeId);
}
