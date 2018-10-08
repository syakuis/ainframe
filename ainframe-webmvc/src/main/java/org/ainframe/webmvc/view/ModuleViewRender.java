package org.ainframe.webmvc.view;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.ainframe.context.module.Module;
import org.ainframe.context.module.ModuleContext;
import org.ainframe.web.config.model.Config;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 27.
 */
@Slf4j
public class ModuleViewRender implements ModuleView {
  private final WebViewAdapter webViewAdapter;
    @Getter
    private final Module module;
    @Getter
    private final Module parentModule;

    /**
    * 현재 모듈의 경로를 설정한다.
    */
    @Getter
    private String modulePath;

    @Getter
    private String browserTitle;

    private String skin;

    /**
    * 스킨 템플릿을 설정한다.
    */
    private String template;

    /**
    * 스킨 템플릿 상대 경로를 설정한다.
    */
    @Getter
    private String templatePath;

    /**
    * templatePath 를 포함한 스킨 템플릿 파일 경로를 설정한다.
    */
    @Getter
    private String templateFile;

    public ModuleViewRender(WebViewAdapter webViewAdapter, ModuleContext moduleContext, String moduleId) {
      this.webViewAdapter = webViewAdapter;

      this.module = moduleContext.getModule(moduleId);

      if (this.module == null) {
          throw new IllegalArgumentException("module object must not be null.");
      }

      this.modulePath = WebViewUtils.getModulePath(this.module.getModuleName(), moduleId);

      if (!Objects.equals(moduleId, module.getModuleName())) {
          this.parentModule = moduleContext.getModule(this.module.getModuleName());
      } else {
          this.parentModule = module;
      }
    }

    public void render(String template) {
        if (template == null || template.length() == 0) {
            throw new IllegalArgumentException("template must not be null.");
        }

        this.template = template;

        // 기본 타이틀
        this.changeBrowserTitle(module.getBrowserTitle());

        // 기본 스킨
        this.defaultSkinAndTemplate();
    }

    /**
     * 기본으로 사용할 스킨 폴더를 구한다. 조건: 관리자여부 > 테마 사용여부 > 스킨 폴터에 템플릿 존재여부
     * 스킨 폴더가 존재하지 않으면 {@link Config#getBasicSkin()} 사용한다.
     * @param template
     * @return
     */
    private String getDefaultSkin(String template) {
        if (WebViewUtils.isAdminTemplate(template)) {
            return this.webViewAdapter.getDefaultAdminSkin();
        }

        if (this.module.isOnlyUseTheme()) {
            return StringUtils.defaultIfEmpty(
              this.webViewAdapter.getConfigSkin(), this.webViewAdapter.getConfigBasicSkin());
        }

        return StringUtils.defaultIfEmpty(this.module.getSkin(), this.webViewAdapter.getDefaultSkin());
    }

    @Override
    public void changeBrowserTitle(String browserTitle) {
        this.changeBrowserTitle(browserTitle, false);
    }


    /**
     * 브라우저 타이틀을 변경한다. titleOverwrite 여부와 상관없이 변경된다.
     * @param browserTitle 브라우저 타이틀
     * @param must 무조건 변경 여부
     */
    @Override
    public void changeBrowserTitle(String browserTitle, boolean must) {
        if (this.webViewAdapter.isConfigBrowserTitleOverwrite() || must) {
            this.browserTitle = browserTitle;
        } else {
            log.warn("브라우저 타이틀을 변경할 수 없게 설정되어있다.");
        }
    }

    /**
     * @param template 스킨 템플릿 파일
     * @see this.changeSkinAndTemplate
     */
    @Override
    public void changeTemplate(String template) {
        this.changeSkinAndTemplate(this.skin, template);
    }

    /**
     * @param skin 스킨 폴더
     * @see this.changeSkinAndTemplate
     */
    @Override
    public void changeSkin(String skin) {
        this.changeSkinAndTemplate(skin, this.template);
    }

    @Override
    public void changeSkinAndTemplate(String skin, String template) {
        if (skin == null || skin.length() == 0) {
            throw new IllegalArgumentException("skin must not be null");
        }

        if (template == null || template.length() == 0) {
            throw new IllegalArgumentException("template must not be null.");
        }

        this.skin = skin;
        this.template = template;

        this.templatePath = createTemplatePath(this.module.getModuleName(), this.skin);
        this.templateFile = createTemplateFile(this.templatePath, this.template);
    }

    /**
     * 모듈의 스킨 경로를 완성한다. 모듈과 스킨 값을 필수이고 템플릿은 선택사항이다.
     * @param moduleName moduleName required
     * @param skin skin required
     * @return String
     */
    public static String createTemplatePath(String moduleName, String skin) {
        if (StringUtils.isEmpty(moduleName) || StringUtils.isEmpty(skin)) {
            throw new IllegalArgumentException("경로를 조합하기 위한 인수가 올바르지 않다.");
        }

        return new StringBuilder("/modules/").append(moduleName).append("/skins/").append(skin).toString();
    }

    /**
     * 모듈의 스킨 경로를 얻어 스킨 템플릿 파일 경로를 완성한다.
     * @param path skin path required
     * @param template template required
     * @return String
     */
    public static String createTemplateFile(String path, String template) {
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(template)) {
            throw new IllegalArgumentException("경로를 만들기 위한 인수가 올바르지 않다.");
        }
        return new StringBuilder(path).append('/').append(template).toString();
    }

    /**
     * 기본 스킨과 템플릿을 설정하고 실제 파일이 존재하지 않으면 기본 스킨과 템플릿을 설정한다.
     */
    private void defaultSkinAndTemplate() {
        this.changeSkinAndTemplate(this.getDefaultSkin(template), template);

        if (!this.webViewAdapter.isTemplateExists(this.templateFile)) {
            log.warn("스킨에 템플릿 파일이 존재하지 않습니다. 기본 설정으로 변경됨 : skin = {} : template = {}", skin, template);
            this.changeSkinAndTemplate(this.webViewAdapter.getConfigBasicSkin(), this.template);
        }
    }


    /**
     * 기본으로 사용될 layoutIdx 결정한다.
     * admin 인 경우 defaultAdminLayoutIdx 사용하고 테마 사용인 경우 {@link Config} 에 설정된 layoutIdx 을 사용한다.
     * 그외 모듈에 설정된 layoutIdx 사용한다.
     * @return layoutIdx
     */
    public String getDefaultLayoutIdx(String template) {
        // 관리자 여부에 따라 기본 스킨과 레이아웃이 변경됨.
        if (WebViewUtils.isAdminTemplate(template)) {
            return this.webViewAdapter.getDefaultAdminLayoutIdx();
        } else if (this.module.isOnlyUseTheme()) {
            return this.webViewAdapter.getConfigLayoutIdx();
        }

        return StringUtils.defaultIfEmpty(this.module.getLayoutIdx(), this.webViewAdapter.getConfigLayoutIdx());
    }
}
