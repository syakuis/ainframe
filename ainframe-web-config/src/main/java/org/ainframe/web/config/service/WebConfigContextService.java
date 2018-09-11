package org.ainframe.web.config.service;

import org.ainframe.web.config.domain.ConfigEntity;
import org.ainframe.web.config.model.ConfigDetails;
import org.ainframe.context.ConfigContextService;
import org.ainframe.context.model.Config;
import org.ainframe.web.config.domain.ConfigObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ConfigEntity 를 Config 변형하고 불변 객체를 반환한다.
 * 해당 서비스 확장하여 캐시를 구성하기도 한다.
 *
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

    /**
     * {@link ModelMapper} 를 활용하여 타입을 변환한다. 호출될때마다 인스턴스 한다.
     * @param configObject {@link ConfigEntity}
     * @return Config
     */
    private Config transform(ConfigObject configObject) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(configObject, ConfigDetails.class);
    }

    @Override
    public Config getConfig() {
        return this.transform(this.configService.getConfig());
    }
}
