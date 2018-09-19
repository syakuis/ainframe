package org.ainframe.context;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
@Component
public class LayoutContext {
    private LayoutContextService layoutContextService;

    @Autowired
    @Qualifier("cacheLayoutContextService")
    public void setLayoutContextService(LayoutContextService layoutContextService) {
        this.layoutContextService = layoutContextService;
    }

    public List<Layout> getLayouts() {
        return this.layoutContextService.getLayouts();
    }

    public Layout getLayout(String layoutIdx) {
        return this.layoutContextService.getLayout(layoutIdx);
    }
}
