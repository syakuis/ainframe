package org.ainframe.web.view;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;

import org.ainframe.web.config.model.Config;
import org.ainframe.web.module.model.Module;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * todo 실제 템플릿 파일 존재 여부 체크
 * todo 레이아웃 구현
 * todo 메뉴 구현
 * 기본적으로 설정된 모듈 정보를 다시 설정할 수 있도록 기능을 제공한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 28.
 */
@Slf4j
@Getter
public class ModuleViewRender implements ModuleView {
    private final ModelAndView modelAndView;

    private final Config config;
    private final Module module;
    private final Module parentModule;
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

    public ModuleViewRender(Config config, Module module, Module parentModule) {
        if (module == null) {
            throw new IllegalArgumentException("args 는 null 을 입력할 수 없다.");
        }

        this.config = config;
        this.module = module;
        this.parentModule = parentModule;

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
        if (template == null || template.length() == 0) {
            throw new IllegalArgumentException("template 인자는 꼭 입력해야 한다.");
        }
        this.template = template;
    }

    /**
     * @param skin 스킨 폴더
     * @see this.changeSkinAndTemplate
     */
    public void changeSkin(String skin) {
        if (skin == null || skin.length() == 0) {
            throw new IllegalArgumentException("skin 인자는 꼭 입력해야 한다.");
        }
        this.skin = skin;
    }

    /**
     * 템플릿 경로에 실제 파일이 존재하는 지 판단한다.
     * @param path 상대 경로
     * @return boolean
     */
    private boolean isResourceExists(String path) {
        ServletRequestAttributes servlet = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        ServletContext servletContext = servlet.getRequest().getServletContext();
        try {
          URL url = servletContext.getResource(path);
          return url != null;
        } catch (MalformedURLException e) {
          log.debug(e.getMessage());
        }

        return false;
    }

    /**
     * 스킨 폴더에서 템플릿 파일이 존재하는 지 판단한다.
     * @param skin 스킨명
     * @param template 템플릿명
     * @return boolean
     */
    private boolean isSkinTemplateExists(String moduleName, String skin, String template) {
        String templateFile = ModuleViewUtils.getSkinPath(moduleName, skin, template);
        return this.isResourceExists("템플릿 경로" + templateFile);
    }

    /**
     * 기본으로 사용할 스킨 폴더를 구한다. 조건: 관리자여부 > 테마 사용여부 > 스킨 폴터에 템플릿 존재여부
     * 스킨 폴더가 존재하지 않으면 {@link Config#getBasicSkin()} 사용한다.
     * @param skin
     * @param template
     * @return
     */
    private String getDefaultSkin(String skin, String template) {
        if (ModuleViewUtils.isAdminTemplate(template)) {
            return ModuleViewResolver.ADMIN_SKIN;
        }

        String finalSkin = StringUtils.defaultIfEmpty(skin, ModuleViewResolver.SKIN);
        if (this.module.isOnlyUseTheme()) {
            finalSkin =  StringUtils.defaultIfEmpty(config.getSkin(), config.getBasicSkin());
        }

        return finalSkin;
    }

    /**
     * 초기 작업을 구현한다.
     * 브라우저 타이틀을 결정한다.
     * 템플릿 폴더 경로와 템플릿 파일 경로를 구한다.
     * @param template 템플릿 파일명
     */
    protected void render(String template) {
        this.changeBrowserTitle(this.module.getBrowserTitle());
        this.changeTemplate(template);
    }

    /**
     * 최종적으로 뷰 모델에 반영될 작업들을 구현한다. lazy 방식으로 처리될 작업들도 여기에 구현한다.
     * 브라우저 타이틀이 덮어쓰기 할 수 없을때 기본 타이트를 적용한다.
     * 테마를 사용할 경우 기본 스킨(테마)으로 설정된다.
     */
    public ModelAndView done() {

        // 실제 템플릿 파일이 존재하는 지 판단하여 최종 스킨을 결정한다.
        // 템플릿 파일이 없으면 기본 스킨의 템플릿을 사용한다.
//        if (!this.isSkinTemplateExists(this.moduleName, this.skin, this.template)) {
//            this.changeSkinAndTemplate(this.config.getBasicSkin(), this.template);
//        }

        // 타이틀을 변경할 수 없는 경우
        if (!this.config.getTitleOverwrite().isValue()) {
            log.warn("브라우저 타이틀을 변경할 수 없게 설정되어있다. 타이틀은 항상 기본 타이틀로 초기화 될 것이다.");
            this.changeBrowserTitle(this.config.getTitle());
        }

        this.changeSkin(this.getDefaultSkin(this.skin, this.template));

        // 최종 스킨 폴더 경로와 템플릿 파일 경로를 구한다.
        String templatePath = ModuleViewUtils.getSkinPath(this.moduleName, this.skin, null);
        String templateFile = ModuleViewUtils.getSkinTemplatePath(templatePath, template);

        this.templatePath = templatePath;
        this.templateFile = templateFile;

        if(log.isDebugEnabled()) {
            log.debug(
                new StringBuilder("\r\n{> ModuleViewResolver <}===============================================\r\n")
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

        return this.modelAndView;
    }
}
