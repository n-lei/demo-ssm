package system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/page")
public class PageController {

    /**
     * 跳转到页面
     */
    @GetMapping("/{page}")
    public String gotoPage(@PathVariable("page") String page){
        System.out.println("------page:" + page);
        return page;
    }

    /**
     * 跳转到页面 - 带form待执行方法
     */
    @GetMapping("/{page}/{formMethod}")
    public ModelAndView gotoPage(@PathVariable("page") String page,
                           @PathVariable("formMethod") String formMethod){
        System.out.println("------page:" + page);
        System.out.println("------formMethod:" + formMethod);
        ModelAndView mav = new ModelAndView(page);
        mav.addObject("formMethod", formMethod);
        return mav;
    }
}