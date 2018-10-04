package org.syaku.example.app.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.syaku.example.app.demo.domain.DemoEntity;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 28.
 */
public interface DemoRepository extends PagingAndSortingRepository<DemoEntity, Long> {

}
