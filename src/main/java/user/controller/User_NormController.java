package user.controller;

import nl.pub.utils.keyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import user.entity.User;
import user.service.UserService;

@Controller
@RequestMapping("/userNorm")
public class User_NormController {

    @Autowired
    private UserService userService;

    /** web根目录文件夹 */
    private String webRootFile = "user_norm/";

    /**
     * 显示所有用户信息 - mav实现
     */
    @RequestMapping("/listAllUsers")
    public ModelAndView listAllUsers(){
        ModelAndView mav = new ModelAndView(webRootFile + "user_list");
        mav.addObject("user", userService.getAllList());
        System.out.println("------mav:" + mav.toString());
        return mav;
    }

    /**
     * 去往添加页面 - mav实现
     */
    @RequestMapping("/goAdd")
    public ModelAndView goAdd() {
        ModelAndView mav = new ModelAndView(webRootFile + "user_edit");
        mav.addObject("method", "op_add");
        return mav;
    }

    /**
     * 去往编辑页面
     * @param id 用户id
     */
    @RequestMapping("/goEdit")
    public ModelAndView goEdit(long id) {
        //System.out.println("======== edit[id]:" + id);
        ModelAndView mav = new ModelAndView(webRootFile + "user_edit");
        mav.addObject("method", "op_edit");
        mav.addObject("user", userService.getInfoById(id));
        System.out.println("------mav:" + mav.toString());
        return mav;
    }

    /**
     * 去往编辑页面
     * @param id 用户id
     */
    @RequestMapping("/goEdit/{id}")
    public ModelAndView goEdit1(@PathVariable("id") long id) {
        System.out.println("======== edit[id]:" + id);
        ModelAndView mav = new ModelAndView(webRootFile + "user_edit");
        mav.addObject("method", "op_edit");
        mav.addObject("user", userService.getInfoById(id));
        System.out.println("------mav:" + mav.toString());
        return mav;
    }

    /**
     * 添加数据之后跳转到列表页面
     */
    @RequestMapping("/doSubmit")
    public ModelAndView doSubmit(@RequestParam(value = "model", required = true) String model,
                                 @RequestParam(value = "method", required = true) String method,
                                 User user){
        System.out.println("======== doSubmit:[model]:" + model + ";[method]" + method + ";[user]" + user);

        ModelAndView mav = new ModelAndView();
        switch(method){
            case "op_add": mav = doAdd(user); break;
            case "op_edit": mav = doEdit(user); break;
            default: mav = goToListAllUsers();
        }
        return mav;
    }

    /**
     * 删除
     * @param id 用户id
     */
    @RequestMapping("/delete")
    public ModelAndView delete(long id) {
        System.out.println("======== delete:[id]:" + id);
        userService.delete(id);
        return goToListAllUsers();
    }

    /**
     * 去往列表页面
     * @return
     */
    public ModelAndView goToListAllUsers() {
        return new ModelAndView("redirect:/" + webRootFile +"/listAllUsers");
//        return new ModelAndView("forward:/listAllUsers");
    }

    /**
     * 添加数据之后跳转到列表页面
     */
    public ModelAndView doAdd(User user){
        user.setId(keyUtil.nextId());
        userService.save(user);
        return goToListAllUsers();
    }

    /**
     * 修改
     * @param user
     */
    public ModelAndView doEdit(User user){
        boolean flag = userService.update(user);
        System.out.println("------user:" + user.toString());
        if(flag){
            return goToListAllUsers();
        } else {
            ModelAndView mav = new ModelAndView(webRootFile + "user_edit");
            mav.addObject("user", user);
            System.out.println("------mav:" + mav.toString());
            return mav;
        }
    }
}