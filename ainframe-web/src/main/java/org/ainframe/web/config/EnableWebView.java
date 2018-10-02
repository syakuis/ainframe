package org.ainframe.web.config;

import java.lang.annotation.*;

import org.springframework.context.annotation.Import;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(WebConfiguration.class)
public @interface EnableWebView {
}
