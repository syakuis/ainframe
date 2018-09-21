package org.ainframe.context;

import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
public interface LayoutContextService {
    Layout getLayout(String layoutIdx);

    /**
     * 전체 레이아웃중 번호화 이름만 얻기 위함
     * @return Map
     */
    Map<String, String> getAllLayoutName();
}
