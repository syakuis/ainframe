package org.ainframe.web.layout.service;

import org.ainframe.web.layout.domain.LayoutEntity;
import org.ainframe.web.layout.repository.LayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 21.
 */
@Service
@Transactional
public class LayoutService {
    private LayoutRepository layoutRepository;

    @Autowired
    public void setLayoutRepository(LayoutRepository layoutRepository) {
        this.layoutRepository = layoutRepository;
    }

    public List<LayoutEntity> getLayouts() {
        return this.layoutRepository.findAll();
    }

    public LayoutEntity getLayoutByLayoutIdx(String layoutIdx) {
        return this.layoutRepository.findOne(layoutIdx);
    }
}
