package org.ainframe.web.menu.repository;

import org.ainframe.web.menu.domain.MenuEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.Repository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
public interface MenuRepository extends Repository<MenuEntity, String> {
    MenuEntity findOne(String menuIdx);

    /**
     * 즉시 로딩으로 menuItem 까지 포함한다.
     * @param menuIdx 메뉴번호
     * @return MenuEntity
     */
    @EntityGraph(value = "MenuEntity.menuItemEntities", type = EntityGraph.EntityGraphType.LOAD)
    MenuEntity findMenuEntitiesByMenuIdx(String menuIdx);
}
