package org.syaku.exemple.app.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.syaku.exemple.app.demo.domain.DemoEntity;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 28.
 */
public interface DemoRepository extends PagingAndSortingRepository<DemoEntity, Long> {

}
