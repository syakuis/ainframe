package org.ainframe.web.view;

import java.util.Objects;

import org.ainframe.web.config.context.ConfigContext;
import org.ainframe.web.module.context.ModuleContext;
import org.ainframe.web.module.model.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 요청한 정보(모듈 정보)를 이용하여 모듈 정보를 조회하여 뷰에서 사용될 데이터를 모아 모델링한다.
 * 뷰에서 사용될 최종 스킨과 레이아웃 그리고 메뉴가 결정된다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 28.
 */
@Component
public class ModuleViewResolver {
    public static final String LAYOUT_TEMPLATE = "layout.ftl";
    public static final String INDEX = "index.ftl";
    public static final String ADMIN_SKIN = "admin";
    public static final String SKIN = "tpl";

    private ModuleContext moduleContext;
    private ConfigContext configContext;

    @Autowired
    public void setModuleContext(ModuleContext moduleContext) {
        this.moduleContext = moduleContext;
    }

    @Autowired
    public void setConfigContext(ConfigContext configContext) {
        this.configContext = configContext;
    }

    // todo isResourceExists
    // todo isSkinTemplateExists
    // todo getDefaultSkin

    /**
     * ModuleViewRender 를 인스턴스한다.
     * @param moduleId 모듈id
     * @return ModuleViewRender
     */
    private ModuleViewRender create(String moduleId) {
        Module module = moduleContext.getModule(moduleId);
        String moduleName =  module.getModuleName();
        Module parentModule = module;
        if (!Objects.equals(moduleId, module.getModuleName())) {
            parentModule = moduleContext.getModule(moduleName);
        }

        return new ModuleViewRender(configContext.getConfig(), module, parentModule);
    }

    /**
     * 새로운 moduleView 를 만들며 moduleId 에 대한 모듈 정보를 moduleView 에 설정한다.
     * render 동작보다 우선 호출되어야 한다.
     * 의존하는 객체를 lazy 방식으로 작성해야 한다.
     * @param moduleId 모듈 ID
     * @return ModuleView
     */
    public ModuleView loader(String moduleId) {
        return this.create(moduleId);
    }

    /**
     * 뷰에 사용될 스킨, 레이아웃, 메뉴 정보들을 구하여 설정한다.
     * html 형식의 뷰를 출력할때 사용된다.
     * @param moduleId
     * @param template
     * @return
     */
    public ModuleViewRender render(String moduleId, String template) {
        ModuleViewRender moduleView = this.create(moduleId);
        moduleView.render(template);
        moduleView.addObject("MV", moduleView);
        return moduleView;
    }


}
