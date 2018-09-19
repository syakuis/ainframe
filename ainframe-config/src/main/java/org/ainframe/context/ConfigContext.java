package org.ainframe.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ConfigContextService 를 이용하여 데이터를 공유한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 * @see ConfigContextService
 * @see Config
 */
@Component
public class ConfigContext {
    private ConfigContextService configContextService;

    @Autowired
    public void setConfigContextService(ConfigContextService configContextService) {
        this.configContextService = configContextService;
    }

    public Config getConfig() {
        return this.configContextService.getConfig();
    }
}
