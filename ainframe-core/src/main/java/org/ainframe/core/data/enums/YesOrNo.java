package org.ainframe.core.data.enums;

/**
 * N, Y 순서를 절대 바꾸면 안된다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 30.
 */
public enum YesOrNo {
    N(false), Y(true);

    private boolean value;

    YesOrNo(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }
}
