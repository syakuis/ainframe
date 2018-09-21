package org.ainframe.web.layout.service;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.ainframe.context.Layout;
import org.ainframe.context.LayoutContextService;
import org.ainframe.web.layout.domain.LayoutEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
@Service
@Transactional
public class WebLayoutContextService implements LayoutContextService {
    private LayoutService layoutService;

    @Autowired
    public void setLayoutService(LayoutService layoutService) {
        this.layoutService = layoutService;
    }

    @Override
    public Layout getLayout(String layoutIdx) {
        return this.layoutService.getLayoutByLayoutIdx(layoutIdx);
    }

    @Override
    public Map<String, String> getAllLayoutName() {
        List<LayoutEntity> layoutEntities = this.layoutService.getLayouts();

        return Maps.transformValues(Maps.uniqueIndex(layoutEntities, new Function<LayoutEntity, String>() {
            @Override
            public String apply(LayoutEntity input) {
                return input.getLayoutIdx();
            }
        }), new Function<LayoutEntity, String>() {
            @Override
            public String apply(LayoutEntity input) {
                return input.getLayoutName();
            }
        });
    }
}
