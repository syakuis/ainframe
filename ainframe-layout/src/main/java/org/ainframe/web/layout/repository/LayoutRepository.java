package org.ainframe.web.layout.repository;

import java.util.List;

import org.ainframe.web.layout.domain.LayoutEntity;
import org.springframework.data.repository.Repository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
public interface LayoutRepository extends Repository<LayoutEntity, String> {
    List<LayoutEntity> findAll();
    LayoutEntity findOne(String layoutIdx);
}
