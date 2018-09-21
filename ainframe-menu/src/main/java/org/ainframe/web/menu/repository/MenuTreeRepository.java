package org.ainframe.web.menu.repository;

import org.ainframe.web.menu.domain.MenuTreeEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
public interface MenuTreeRepository extends Repository<MenuTreeEntity, String> {
    List<MenuTreeEntity> findAll();
    MenuTreeEntity findOne(String menuIdx);
}
