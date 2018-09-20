package org.ainframe.web.config.service;

import org.ainframe.web.config.config.ConfigConfig;
import org.ainframe.web.config.model.Config;
import org.ainframe.web.config.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 */
@Service
@Transactional(readOnly = true)
public class ConfigService {
    private ConfigRepository configRepository;
    private ConfigConfig config;

    @Autowired
    public void setConfigRepository(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Autowired
    public void setConfig(ConfigConfig config) {
        this.config = config;
    }

    public Config getConfig() {
        return this.configRepository.findOneByModuleIdx(config.getModuleIdx());
    }
}
