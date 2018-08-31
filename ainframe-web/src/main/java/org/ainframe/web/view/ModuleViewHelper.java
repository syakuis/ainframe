package org.ainframe.web.view;

import org.ainframe.web.config.context.ConfigContext;
import org.ainframe.web.config.model.Config;
import org.ainframe.web.module.context.ModuleContext;
import org.ainframe.web.module.model.Module;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 */
public final class ModuleViewHelper {
    private final Module module;
    private final Config config;

    public ModuleViewHelper(Module module, Config config) {
        this.module = module;
        this.config = config;
    }

    /**
     * 기본으로 사용할 스킨 폴더를 구한다. 조건: 관리자여부 > 테마 사용여부 > 스킨 폴터에 템플릿 존재여부
     * 스킨 폴더가 존재하지 않으면 {@link DefaultModuleView#getBasicSkin()} 사용한다.
     * @param skin
     * @param template
     * @return
     */
    protected String getDefaultSkin(String skin, String template) {
        if (ModuleViewUtils.isAdminTemplate(template)) {
            return ModuleViewResolver.ADMIN_SKIN;
        }

        String finalSkin = skin;
        if (this.module.isOnlyUseTheme()) {
            finalSkin =  StringUtils.defaultIfEmpty(config.getSkin(), skin);
        }

        if (finalSkin != null) {
            if (!this.isSkinTemplateExists(finalSkin, template)) {
                return this.config.getBasicSkin();
            } else {
                return finalSkin;
            }
        }

        throw new IllegalArgumentException("the skin must not be null.");
    }
}
