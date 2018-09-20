package org.ainframe.web.menu.repository;

import java.util.List;

import org.ainframe.web.menu.domain.MenuDetailsEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.Repository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
public interface MenuRepository extends Repository<MenuDetailsEntity, String> {
    List<MenuDetailsEntity> findAll();
    MenuDetailsEntity findOne(String menuIdx);

    /**
     * 즉시 로딩으로 menuItem 까지 포함한다.
     * @param menuIdx 메뉴번호
     * @return MenuDetailsEntity
     */
    @EntityGraph(value = "MenuDetailsEntity.menuItemEntities", type = EntityGraph.EntityGraphType.LOAD)
    MenuDetailsEntity findMenuEntitiesByMenuIdx(String menuIdx);
}
