package org.ainframe.web.config.service;

import org.ainframe.context.ConfigContextService;
import org.ainframe.web.config.domain.ConfigEntity;
import org.ainframe.web.config.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 * @see ConfigService
 * @see ConfigEntity
 */
@Service
@Transactional(readOnly = true)
public class WebConfigContextService implements ConfigContextService {
    private ConfigService configService;

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    public Config getConfig() {
        return this.configService.getConfig();
    }
}
