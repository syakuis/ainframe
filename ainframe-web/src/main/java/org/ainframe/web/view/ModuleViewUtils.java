package org.ainframe.web.view;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 6. 22.
 */
@Slf4j
public final class ModuleViewUtils {
  private ModuleViewUtils() {
  }

  /**
   * template 가 admin 템플릿인지 판단한다.
   * "admin." 으로 시작되는 파일명이여야 한다.
   * @param template
   * @return
   */
  public static boolean isAdminTemplate(String template) {
    if (StringUtils.isEmpty(template)) {
      return false;
    }
    return template.startsWith("admin.");
  }

  /**
   * 현재 모듈의 경로를 반환한다.
   * @return
   */
  public static String getModulePath(String moduleName, String moduleId) {
    StringBuilder builder = new StringBuilder("/");
    builder.append(moduleName);

    if (!StringUtils.equals(moduleName, moduleId)) {
      builder.append("/").append(moduleId);
    }

    return builder.toString();
  }

  /**
   * 모듈의 스킨 경로를 완성한다. 모듈과 스킨 값을 필수이고 템플릿은 선택사항이다.
   * @param module moduleName required
   * @param skin skin required
   * @param template skin template
   * @return String
   */
  public static String getSkinPath(String module, String skin, String template) {
    if (StringUtils.isEmpty(module) || StringUtils.isEmpty(skin)) {
      log.error("module = {}, skin = {} 경로를 만들기 위한 인수가 올바르지 않다.", module, skin);
      throw new IllegalArgumentException("경로를 만들기 위한 인수가 올바르지 않다.");
    }

    StringBuilder path = new StringBuilder("/modules/");
    path.append(module).append("/skins/").append(skin);

    if (StringUtils.isNotEmpty(template)) {
      path.append('/').append(template);
    }

    return path.toString();
  }

  /**
   * 모듈의 스킨 경로를 얻어 스킨 템플릿 파일 경로를 완성한다.
   * @param path skin path required
   * @param template template required
   * @return String
   */
  public static String getSkinTemplatePath(String path, String template) {
    if (StringUtils.isEmpty(path) || StringUtils.isEmpty(template)) {
      throw new IllegalArgumentException("경로를 만들기 위한 인수가 올바르지 않다.");
    }
    return new StringBuilder(path).append('/').append(template).toString();
  }


  /**
   * 레이아웃을 사용할 수 있는 지 판단한다.
   * @param layoutIdx 레이아웃 번호
   * @return boolean
   */
  public static boolean isUseLayout(String layoutIdx, Map<String, Object> layoutContext) {
    return (layoutContext != null && !layoutContext.isEmpty() &&
        layoutIdx != null && layoutIdx.length() > 0);
  }
}
