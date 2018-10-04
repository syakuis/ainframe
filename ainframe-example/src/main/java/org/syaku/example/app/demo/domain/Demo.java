package org.syaku.example.app.demo.domain;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 1.
 */
public interface Demo {
    long getDemoIdx();

    String getSubject();

    String getContents();

    java.util.Date getCreationDate();
}
