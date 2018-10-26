package com.ant.be.admin;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ant.be.common.constant.Const;
import com.ant.be.dto.UserDto;
import com.ant.be.entity.Users;
import com.ant.be.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 登录后返回页面权限信息 permissions:页面权限集合 userName:用户名 userId:用户id
     * 
     * @return
     */
    @RequestMapping(value = "/getUserBySessionId/{id}", method = RequestMethod.GET)
    public Object getUserBySessionId(@PathVariable("id") String id) {
        return userService.getUserBySessionId(id);
    }
    
    /**
     * 给用户发送验证码
     * @param user
     * @return
     */
    @RequestMapping(value = "/getUserBySessionId/sendCode/{id}", method = RequestMethod.PUT)
    public Object register(@PathVariable("id") String id) {
        return userService.sendVerifyCode(id);
    }
    
    /**
     * 登录后返回页面权限信息 permissions:页面权限集合 userName:用户名 userId:用户id
     * 
     * @return
     */
    @RequestMapping(value = "/getUserByWechatSessionId", method = RequestMethod.GET)
    public Object getUserByWechatSessionId(@PathParam(value = "token") String token) {
        return userService.getUserByWechatSessionId(token);
    }
    
    /**
     * id查询
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) {
        return userService.searchSingle(id).get();
    }
    
    /**
     * 多条件查询
     * 
     * @param pg
     * @return
     * @throws ClassNotFoundException
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public Object query(@RequestBody UserDto user) throws ClassNotFoundException {
        return userService.search(user);
    }
    
    /**
     * 多条件查询
     * 
     * @param pg
     * @return
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Object wechatQuery(@ModelAttribute UserDto user) throws ClassNotFoundException {
        if (user.getSearchTecherOrStrudentByCourseId() != null && user.getSearchTecherOrStrudentByCourseId().equals("1")) {
            return userService.searchTecherByCourseDetailId(user);
        } else if (user.getSearchTecherOrStrudentByCourseId() != null && user.getSearchTecherOrStrudentByCourseId().equals("2")) {
            return userService.searchStrudentByCourseId(user);
        } else {
            return userService.search(user);
        }
    }
    
    /**
     * 新建
     * 
     * @param user
     */
    @RequestMapping(method = RequestMethod.POST)
    public Object creat(@RequestBody Users user) {
        return userService.creat(user);
    }
    
    /**
     * 新建
     * 
     * @param user
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Object wechatCreat(@ModelAttribute Users user) {
        return userService.creat(user);
    }
    
    /**
     * 更新
     * 
     * @param user
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable("id") Long id, @RequestBody Users user) {
        return userService.update(id, user, Const.update);
    }
    
    /**
     * 更新
     * 
     * @param user
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @Transactional
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public Object wechatUpdate(@PathVariable("id") Long id, @ModelAttribute Users user) {
        return userService.update(id, user, Const.update);
    }
    
    /**
     * 删除
     * 
     * @param id
     */
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Long id) {
        return userService.update(id, null, Const.delete);
    }
    
    /**
     * 删除
     * 
     * @param id
     */
    @Transactional
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public Object wechatDelete(@PathVariable("id") Long id) {
        return userService.update(id, null, Const.delete);
    }
    
}
