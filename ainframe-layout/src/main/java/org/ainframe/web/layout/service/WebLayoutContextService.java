package org.ainframe.web.layout.service;

import java.util.List;

import org.ainframe.context.LayoutContextService;
import org.ainframe.context.Layout;
import org.ainframe.web.layout.domain.LayoutEntity;
import org.ainframe.web.layout.repository.LayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
@Service
@Transactional
public class WebLayoutContextService implements LayoutContextService {
    private LayoutRepository layoutRepository;

    @Autowired
    public void setLayoutRepository(LayoutRepository layoutRepository) {
        this.layoutRepository = layoutRepository;
    }

    private Layout transform(LayoutEntity layoutEntity) {
        return Layout.builder()
            .layoutIdx(layoutEntity.getLayoutIdx())
            .layoutName(layoutEntity.getLayoutName())
            .menuIdx(layoutEntity.getMenuIdx())
            .build();
    }

    @Override
    public Layout getLayout(String layoutIdx) {
        return this.transform(this.layoutRepository.findOne(layoutIdx));
    }

    @Override
    public List<Layout> getLayouts() {
        return Lists.newArrayList(Lists.transform(this.layoutRepository.findAll(), new Function<LayoutEntity, Layout>() {
            @Override
            public Layout apply(LayoutEntity input) {
                return transform(input);
            }
        }));
    }

}
