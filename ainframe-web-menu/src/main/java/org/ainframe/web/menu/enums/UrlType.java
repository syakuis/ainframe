package org.ainframe.web.menu.enums;

/**
 * todo 타입을 다시 설정한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 */
public enum UrlType {
    PATH(0, "일반"), LINK(1, "링크"), JAVASCRIPT(2, "자바스크립트"), CUSTOM(3, "직접입력"), ROUTER(4, "라우터");

    private final int code;
    private final String label;

    UrlType(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() { return code; }
    public String getLabel() { return label; }

    public static UrlType valueOf(int code) {
        switch(code) {
            case 0 : return PATH;
            case 1 : return LINK;
            case 2 : return JAVASCRIPT;
            case 3 : return CUSTOM;
            case 4 : return ROUTER;
            default : throw new IllegalArgumentException("Unknown Enum Value");
        }
    }
}
