package com.ant.be.shiro.factory;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.ant.be.common.constant.cache.Cache;
import com.ant.be.common.constant.cache.CacheKey;
import com.ant.be.entity.Role;
import com.ant.be.entity.Users;
import com.ant.be.repository.RoleRepository;
import com.ant.be.repository.UserRepository;
import com.ant.be.utils.SpringContextHolder;
import com.ant.be.utils.ToolUtil;

/**
 * 常量的生产工厂
 *
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    private RoleRepository roleRepository = SpringContextHolder.getBean(RoleRepository.class);
    private UserRepository userRepository = SpringContextHolder.getBean(UserRepository.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    /**
     * 根据用户id获取用户名称
     *
     */
    @Override
    public String getUserNameById(Integer userId) {
        Users user = userRepository.getOne(new Long((long)userId));
        if (user != null) {
            return user.getName();
        } else {
            return "--";
        }
    }

    /**
     * 根据用户id获取用户账号
     *
     */
    @Override
    public String getUserAccountById(Integer userId) {
        Users user = userRepository.getOne(new Long((long)userId));
        if (user != null) {
            return user.getName();
        } else {
            return "--";
        }
    }

    /**
     * 通过角色id获取角色名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    public String getSingleRoleName(Integer roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleRepository.getOne(new Long((long)roleId));
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    /**
     * 通过角色id获取角色英文名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
    public String getSingleRoleTip(Integer roleId) {
//        if (0 == roleId) {
//            return "--";
//        }
//        Role roleObj = roleRepository(roleId);
//        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
//            return roleObj.getTips();
//        }
        return null;
    }


    /**
     * 获取性别名称
     */
    @Override
    public String getSexName(Integer sex) {
        return null;
    }

	@Override
	public String getStatusName(Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCacheObject(String para) {
		// TODO Auto-generated method stub
		return null;
	}




    /**
     * 获取被缓存的对象(用户删除业务)
     */
//    @Override
//    public String getCacheObject(String para) {
//        return LogObjectHolder.me().get().toString();
//    }



}
