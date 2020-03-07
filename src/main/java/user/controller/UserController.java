package user.controller;

import com.github.pagehelper.PageInfo;
import springMvc.pojo.RespBody;
import nl.pub.utils.KeyUtil;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /** 访问model */
    private String model = "users";
    /** web根目录文件夹 */
    private String webRootFile = "user/";
    /** 列表页面路径 */
    private String listPageUrl = webRootFile + "user_list";

    /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping("")
    public String goList(){
        return listPageUrl;
    }

    /**
     * 列表页面展示分页数据
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("/paging")
    @ResponseBody
    public RespBody goList(
            @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageShowNumber", defaultValue = "10") Integer pageShowNumber){
        PageInfo<User> pageInfo = userService.getPageList(pageNumber, pageSize, pageShowNumber);
        return RespBody.success().addData("pageInfo", pageInfo);
    }

    /**
     * 查询单条数据
     * @param id 用户id
     */
    @GetMapping("/data/{id}")
    @ResponseBody
    public RespBody goEdit(@PathVariable("id") long id) {
        System.out.println("======== edit[id]:" + id);
        return RespBody.success().addData("field", userService.getInfoById(id));
    }

    /**
     * 新增
     */
    @PostMapping("")
    @ResponseBody
    public ModelAndView doAdd(User user){
        user.setId(KeyUtil.nextId());
        userService.save(user);
        return PageUtil.getListPage(this.model);
    }

    /**
     * 修改
     */
    @PutMapping("")
    @ResponseBody
    public ModelAndView doEdit(User user){
//        System.out.println("------data:" + user.toString());
        boolean flag = userService.update(user);
        if(flag){
            return PageUtil.getListPage(this.model);
        } else {
            return PageUtil.getPage(this.model + "/data/" + user.getId());
        }
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return listPageUrl;
    }
}