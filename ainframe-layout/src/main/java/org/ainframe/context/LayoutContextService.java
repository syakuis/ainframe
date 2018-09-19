package org.ainframe.context;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
public interface LayoutContextService {
    Layout getLayout(String layoutIdx);
    List<Layout> getLayouts();
}
