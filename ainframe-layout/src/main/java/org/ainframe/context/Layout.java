package org.ainframe.context;

import java.io.Serializable;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
public interface Layout extends Serializable {
    String getLayoutIdx();
    String getMenuIdx();
    String getLayoutName();
    String getLayoutTemplate();
}
