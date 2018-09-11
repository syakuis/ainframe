package org.ainframe.core.util;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 7.
 */
public final class PathUtils {
    public static String getRootClassPath() {
        return PathUtils.class.getClass().getResource("/").getPath();
    }
}
