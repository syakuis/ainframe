package org.ainframe.core.util;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring 빈이 생성될때 혹은 클래스가 인스턴스될때 노출하고자 하는 정보를 출력하기 위해 사용된다.
 * 빈 생성여부와 현재 상태정보를 출력할때 유용한다. 서비스가 구동될때 한번만 호출될 수 있을때에만 사용한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 12.
 */
@Slf4j
public final class Label {
    private StringBuilder stringBuilder;
    private static final String HEADER = "\r\n/--------- AINFRAME >>>";
    private static final String FOOTER = "\r\n----------/";

    public Label() {
        this.stringBuilder = new StringBuilder(HEADER);
    }

    public Label add(Object text) {
        stringBuilder.append(text);
        return this;
    }

    public Label title(Object text) {
        return this.first("________ ").add(text).line();
    }

    public Label first(Object text) {
        return this.line().add(text);
    }

    public Label line() {
        return this.br().add("| ");
    }

    public Label br() {
        return this.add("\r\n");
    }

    private String done() {
        return this.stringBuilder.append(FOOTER).toString();
    }

    public void print() {
        System.out.println(this.done());
    }

    public void debug() {
        log.debug(this.done());
    }

    public void error() {
        log.error(this.done());
    }

    public void warn() {
        log.warn(this.done());
    }

    public void info() {
        log.info(this.done());
    }
}
