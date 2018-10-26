package com.ant.be.shiro.factory;

/**
 * 常量生产工厂的接口
 *
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     */
    String getUserNameById(Integer userId);

    /**
     * 根据用户id获取用户账号
     *
     */
    String getUserAccountById(Integer userId);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Integer roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Integer roleId);

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);

    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);


    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);

}
