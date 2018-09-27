package org.ainframe.web.view;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 6. 22.
 */
@Slf4j
public final class WebViewUtils {
    private WebViewUtils() {
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
     * 레이아웃을 사용할 수 있는 지 판단한다.
     * @param layoutIdx 레이아웃 번호
     * @return boolean
     */
    public static boolean isUseLayout(String layoutIdx, Map<String, Object> layoutContext) {
        return (layoutContext != null && !layoutContext.isEmpty() &&
            layoutIdx != null && layoutIdx.length() > 0);
    }
}
