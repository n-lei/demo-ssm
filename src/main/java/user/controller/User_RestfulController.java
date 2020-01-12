package user.controller;

import nl.pub.utils.keyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.utils.PageUtil;
import user.entity.User;
import user.service.UserService;

/**
 * 用户信息
 * @desc restful方式
 */
@Controller
@RequestMapping("/user_rest")
public class User_RestfulController {

    @Autowired
    private UserService userService;

    /** 访问model */
    private String model = "user_rest";
    /** web根目录文件夹 */
    private String webRootFile = "user_rest/";
    /** 编辑页面路径 */
    private String editPageUrl = webRootFile + "user_edit";
    /** 列表页面路径 */
    private String listPageUrl = webRootFile + "user_list";

    /**
     * 跳转到列表页面
     */
    @GetMapping("")
    public ModelAndView goList(){
        ModelAndView mav = new ModelAndView(listPageUrl);
        mav.addObject("data", userService.getUserList());
        System.out.println("------mav:" + mav.toString());
        return mav;
    }

    /**
     * 跳转到新增页面
     */
    @GetMapping("/page")
    public ModelAndView goAdd() {
        ModelAndView mav = PageUtil.getAddPage(editPageUrl);
        System.out.println("------mav:" + mav.toString());
        return mav;
    }

    /**
     * 跳转到编辑页面
     * @param id 用户id
     */
    @GetMapping("/page/{id}")
    public ModelAndView goEdit(@PathVariable("id") long id) {
        System.out.println("======== edit[id]:" + id);
        ModelAndView mav = PageUtil.getEditPage(editPageUrl);
        mav.addObject("data", userService.getInfoById(id));
        System.out.println("------mav:" + mav.toString());
        return mav;
    }

    /**
     * 新增
     */
    @PostMapping("")
    public ModelAndView doAdd(User user){
        user.setId(keyUtil.nextId());
        userService.save(user);
        return PageUtil.getListPage(this.model);
    }

    /**
     * 修改
     */
    @PutMapping("")
    public ModelAndView doEdit(User user){
        System.out.println("------data:" + user.toString());
        boolean flag = userService.update(user);
        if(flag){
            return PageUtil.getListPage(this.model);
        } else {
            return new ModelAndView("redirect:/" + this.model + "/page/" + user.getId());
            /*ModelAndView mav = PageUtil.getEditPage(editPageUrl);
            mav.addObject("data", user);
            System.out.println("------mav:" + mav.toString());
            return mav;*/
        }
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return listPageUrl;
    }
}