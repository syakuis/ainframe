package org.ainframe.web.view;

import org.ainframe.web.module.model.Module;
import org.springframework.web.servlet.ModelAndView;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 28.
 */
@Slf4j
@Getter
public class ModuleViewRender implements ModuleView {
    private final ModelAndView modelAndView;

    private final Module module;
    private final String moduleName;
    private final String moduleId;
    private final String moduleIdx;
    /**
    * 현재 모듈의 경로를 설정한다.
    */
    private final String modulePath;
    private String browserTitle;

    private String skin;

    /**
    * 스킨 템플릿을 설정한다.
    */
    private String template;

    /**
    * 스킨 템플릿 상대 경로를 설정한다.
    */
    private String templatePath;

    /**
    * templatePath 를 포함한 스킨 템플릿 파일 경로를 설정한다.
    */
    private String templateFile;

    public ModuleViewRender(Module module) {
        if (module == null) {
            throw new IllegalArgumentException("args 는 null 을 입력할 수 없다.");
        }

        this.module = module;
        this.moduleName = module.getModuleName();
        this.moduleId = module.getModuleId();
        this.moduleIdx = module.getModuleIdx();
        this.modulePath = ModuleViewUtils.getModulePath(this.moduleName, this.moduleId);
        this.modelAndView = new ModelAndView(ModuleViewResolver.INDEX);
    }

    /**
     * {@link ModelAndView#addObject(Object)}
     * @param attributeName String key
     * @param attributeValue Object value
     */
    public void addObject(String attributeName, Object attributeValue) {
        this.modelAndView.addObject(attributeName, attributeValue);
    }

    /**
     * 브라우저 타이틀을 변경한다. titleOverwrite 여부와 상관없이 변경된다.
     * @param browserTitle 브라우저 타이틀
     */
    public void changeBrowserTitle(String browserTitle) {
    this.browserTitle = browserTitle;
  }

    /**
     * @param template 스킨 템플릿 파일
     * @see this.changeSkinAndTemplate
     */
    public void changeTemplate(String template) {
    this.changeSkinAndTemplate(this.skin, template);
  }

    /**
     * @param skin 스킨 폴더
     * @see this.changeSkinAndTemplate
     */
    public void changeSkin(String skin) {
        this.changeSkinAndTemplate(skin, this.template);
    }

    /**
     * 모듈 스킨과 스킨 템플릿을 변경한다. 스킨 템플릿 파일 존재여부와 상관없이 설정된다.
     * @param skin 스킨 폴더
     * @param template 스킨 템플릿 파일
     */
    public void changeSkinAndTemplate(String skin, String template) {
        if (skin == null) {
            // todo runtime exception 필요.
            log.debug("skin 은 null 일 수 없다.");
            return;
        }
        String templatePath = ModuleViewUtils.getSkinPath(this.moduleName, skin, null);
        String templateFile = ModuleViewUtils.getSkinTemplatePath(templatePath, template);

        this.templatePath = templatePath;
        this.templateFile = templateFile;
        this.template = template;
        this.skin = skin;
    }

    /**
    * 동작이 호출되기 전에 Layout 과 MenuContext 객체를 얻어야 한다.
    * 인스턴스된 moduleView 를 이용하여 레이아웃와 스킨 경로를 만든다.
    * skin 과 basicSkin 둘다 null 이면 안된다.
    */
    public void render(String template) {
        this.changeSkinAndTemplate(this.getDefaultSkin(this.skin, template), template);
    }

    public ModelAndView done() {

        if(log.isDebugEnabled()) {
            log.debug(
                new StringBuilder("\r\n{> ModuleViewResolver <}===============================================\r\n")
                    .append("> module : ").append(module).append("\r\n")
                    .append("> moduleId : ").append(this.moduleId).append("\r\n")
                    .append("> moduleIdx : ").append(this.moduleIdx).append("\r\n")
                    .append("> path : ").append(this.modulePath).append("\r\n")
                    /*.append("> layout : ").append(this.layout).append("\r\n")
                    .append("> layoutIdx : ").append(this.layoutIdx).append("\r\n")
                    .append("> layoutPath : ").append(this.layoutPath).append("\r\n")
                    .append("> layoutFile : ").append(this.layoutFile).append("\r\n")
                    .append("> menuIdx : ").append(this.menuIdx).append("\r\n")*/
                    .append("> skin : ").append(this.skin).append("\r\n")
                    .append("> template : ").append(this.template).append("\r\n")
                    .append("> templatePath : ").append(this.templatePath).append("\r\n")
                    .append("> templateFile : ").append(this.templateFile).append("\r\n")
                    .append("> browserTitle : ").append(this.browserTitle).append("\r\n")
//                    .append("> onlyUseModule : ").append(String.valueOf(this.onlyUseModule)).append("\r\n")
//                    .append("> disableLayout : ").append(String.valueOf(this.disableLayout)).append("\r\n")
//                    .append("> disableMenu : ").append(String.valueOf(this.disableMenu)).append("\r\n")
                    .append("==========================================================================\r\n")
                    .toString()
            );
        }

        // todo
//        this.addObject("menuSelected", super.getMenuFind());
        return this.modelAndView;
    }
}
