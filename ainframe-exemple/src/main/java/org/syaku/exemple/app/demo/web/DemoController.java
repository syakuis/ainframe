package org.syaku.exemple.app.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.ainframe.web.view.WebViewRender;
import org.ainframe.web.view.WebViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.syaku.exemple.app.demo.service.WebDemoService;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 28.
 */
@Slf4j
@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private WebViewResolver webViewResolver;
    @Autowired
    private WebDemoService webDemoService;

    @RequestMapping(name = "", method = RequestMethod.GET)
    public ModelAndView demoList() {
        WebViewRender render = webViewResolver.render("demo", "demo.list.ftl");
        render.addObject("demos", webDemoService.getDemoList());

        log.debug(render.toString());



        return render.done();
    }
}
