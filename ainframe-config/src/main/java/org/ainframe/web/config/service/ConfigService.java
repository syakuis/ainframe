package org.ainframe.web.config.service;

import org.ainframe.web.config.config.ConfigProperties;
import org.ainframe.web.config.model.Config;
import org.ainframe.web.config.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository 에서 기본적으로 사용되는 기능들을 구현했다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 */
@Service
@Transactional(readOnly = true)
public class ConfigService {
    private ConfigRepository configRepository;
    private ConfigProperties configProperties;

    @Autowired
    public void setConfigRepository(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Autowired
    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    public Config getConfig() {
        return this.configRepository.findOneByModuleIdx(configProperties.getModuleIdx());
    }
}
