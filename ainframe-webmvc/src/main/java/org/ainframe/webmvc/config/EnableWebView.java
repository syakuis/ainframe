package org.ainframe.webmvc.config;

import java.lang.annotation.*;

import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(WebConfiguration.class)
public @interface EnableWebView {
}
