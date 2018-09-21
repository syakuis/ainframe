package org.ainframe.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

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

    public Map<String, String> getAllLayoutName() {
        return this.layoutContextService.getAllLayoutName();
    }

    public Layout getLayout(String layoutIdx) {
        return this.layoutContextService.getLayout(layoutIdx);
    }
}
