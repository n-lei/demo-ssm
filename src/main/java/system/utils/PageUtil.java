package system.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageUtil {

    /**
     * 返回新增页面的地址
     * @return
     */
    public static ModelAndView getAddPage(String page) {
        ModelAndView mav = new ModelAndView(page);
//        mav.addObject("formMethod", "POST");
        return mav;
    }

    /**
     * 返回修改页面的地址
     * @return
     */
    public static ModelAndView getEditPage(String page) {
        ModelAndView mav = new ModelAndView(page);
        mav.addObject("formMethod", "PUT");
        return mav;
    }

    /**
     * 返回列表页面的地址
     * @return
     */
    public static ModelAndView getListPage(String page) {
        return new ModelAndView("redirect:/" + page);
    }

    /**
     * 返回指定页面的地址
     * @return
     */
    public static ModelAndView getPage(String page) {
        return new ModelAndView("redirect:/" + page);
    }
}