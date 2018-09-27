package org.ainframe.web.view;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class WebViewResolverTest {

    @Autowired
    private WebViewResolver webViewResolver;

    @Test
    public void test() {
        WebViewRender webView = webViewResolver.render("board", "board.list.ftl");
        webView.done();

        log.debug(webView.toString());
    }
}
