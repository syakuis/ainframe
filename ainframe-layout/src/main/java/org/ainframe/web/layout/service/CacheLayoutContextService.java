package org.ainframe.web.layout.service;

import java.util.List;

import org.ainframe.context.LayoutContextService;
import org.ainframe.context.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
@Service
@Transactional
@CacheConfig(cacheNames = "layoutContext")
public class CacheLayoutContextService implements LayoutContextService {
    private WebLayoutContextService webLayoutContextService;

    @Autowired
    public void setWebLayoutContextService(WebLayoutContextService webLayoutContextService) {
        this.webLayoutContextService = webLayoutContextService;
    }

    @Cacheable(key = "#layoutIdx")
    @Override
    public Layout getLayout(String layoutIdx) {
        return this.webLayoutContextService.getLayout(layoutIdx);
    }

    @Cacheable(key = "'all'")
    @Override
    public List<Layout> getLayouts() {
        return this.webLayoutContextService.getLayouts();
    }

}
