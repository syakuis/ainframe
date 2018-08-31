package org.ainframe.core.module;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 30.
 */
public interface ModuleProperties {
    String getModuleName();

    /**
     * 싱글인 경우 고정 모듈 IDX 를 지정할 수 있다.
     * @return String length 20
     */
    String getModuleIdx();
    boolean isSingle();

}
