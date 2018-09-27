package org.ainframe.web.view;

import org.ainframe.context.ConfigContext;
import org.ainframe.context.LayoutContext;
import org.ainframe.context.MenuContext;
import org.ainframe.context.module.ModuleContext;
import org.ainframe.web.config.WebProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 24.
 */
@Component
public class WebViewResolver {
    private WebProperties webProperties;
    private ModuleContext moduleContext;
    private ConfigContext configContext;
    private LayoutContext layoutContext;
    private MenuContext menuContext;

    @Autowired
    public void setWebProperties(WebProperties webProperties) {
        this.webProperties = webProperties;
    }

    @Autowired
    public void setModuleContext(ModuleContext moduleContext) {
        this.moduleContext = moduleContext;
    }

    @Autowired
    public void setConfigContext(ConfigContext configContext) {
        this.configContext = configContext;
    }

    @Autowired
    public void setLayoutContext(LayoutContext layoutContext) {
        this.layoutContext = layoutContext;
    }

    @Autowired
    public void setMenuContext(MenuContext menuContext) {
        this.menuContext = menuContext;
    }

    /**
     * ModuleViewRender 를 인스턴스한다.
     * @param moduleId 모듈id
     * @return ModuleViewRender
     */
    private WebViewRender create(String moduleId, String template) {
        WebViewRender webViewRender = WebViewRenderBuilder.builder()
            .webProperties(webProperties)
            .configContext(configContext)
            .layoutContext(layoutContext)
            .menuContext(menuContext)
            .moduleContext(moduleContext)
            .moduleId(moduleId)
            .template(template)
            .build();
        return webViewRender;
    }

    /**
     * 새로운 moduleView 를 만들며 moduleId 에 대한 모듈 정보를 moduleView 에 설정한다.
     * render 동작보다 우선 호출되어야 한다.
     * 의존하는 객체를 lazy 방식으로 작성해야 한다.
     * @param moduleId 모듈 ID
     * @return ModuleView
     */
//    public WebViewRender loader(String moduleId) {
//        return this.create(moduleId);
//    }

    /**
     * 뷰에 사용될 스킨, 레이아웃, 메뉴 정보들을 구하여 설정한다.
     * html 형식의 뷰를 출력할때 사용된다.
     * @param moduleId
     * @param template
     * @return
     */
    public WebViewRender render(String moduleId, String template) {
        WebViewRender webViewRender = this.create(moduleId, template);
        return webViewRender;
    }
}
