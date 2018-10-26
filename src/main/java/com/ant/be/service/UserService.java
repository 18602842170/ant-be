package com.ant.be.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.UserDto;
import com.ant.be.entity.Department;
import com.ant.be.entity.Role;
import com.ant.be.entity.Student;
import com.ant.be.entity.Teacher;
import com.ant.be.entity.Users;
import com.ant.be.jpa.Criteria;
import com.ant.be.jpa.factory.Restrictions;
import com.ant.be.mapper.UserMapper;
import com.ant.be.repository.DepartmentRepository;
import com.ant.be.repository.RoleRepository;
import com.ant.be.repository.StudentRepository;
import com.ant.be.repository.TeacherRepository;
import com.ant.be.repository.UserRepository;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.ShiroKit;
import com.ant.be.utils.ToolUtil;
import com.ant.be.utils.UpdateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 检索
     * 
     * @param id
     * @param name
     * @return
     */
    public Map<String, Object> search(UserDto user) {
        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        // mybatis
        if (!("").equals(user.getName()) && null != user.getName()) {
            user.setName("%" + user.getName() + "%");
        }
        List<UserDto> users = userMapper.searchUsers(user.getName(), user.getRoleId(), user.getDeptId(), user.getPhoneNumber());
        if (null != users && users.size() > 0) {
            for (int i = 0; i < users.size(); i++) {
                if (null != users.get(i).getRoleId()) {
                    
                    Optional<Role> roles = roleRepository.findById(users.get(i).getRoleId());
                    users.get(i).setRoleName(roles.get().getName());
                    users.get(i).setPermissionMap(ToolUtil.toJsonObject(roles.get().getPermissionList()));
                }
                if (null != users.get(i).getDeptId()) {
                    Optional<Department> depts = departmentRepository.findById(users.get(i).getDeptId());
                    users.get(i).setDeptName(depts.get().getDeptName());
                }
                
                // 通过用户查到对应在教师或学员表中的数据，取得其id
                // Criteria<Teacher> criteria = new Criteria<>();
                // criteria.add(Restrictions.eq("deleteFlg", false, true));
                // criteria.add(Restrictions.eq("userId", users.get(i).getId(), true));
                // List<Teacher> teacherList = teacherRepository.findAll(criteria);
                // if(teacherList != null && teacherList.size()>0) {
                // users.get(i).setTeacherId(teacherList.get(0).getId());
                // }
                //
                // Criteria<Student> criteria2 = new Criteria<>();
                // criteria2.add(Restrictions.eq("deleteFlg", false, true));
                // criteria2.add(Restrictions.eq("userId", users.get(i).getId(), true));
                // List<Student> studentList = studentRepository.findAll(criteria2);
                // if(studentList != null && studentList.size()>0) {
                // users.get(i).setTeacherId(studentList.get(0).getId());
                // }
                
            }
        }
        PageInfo<UserDto> p = new PageInfo<UserDto>(users);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", p.getTotal());
        map.put("results", p.getList());
        map.put("next", null);
        map.put("previous", null);
        return map;
    }
    
    /**
     * 查询单个
     * 
     * @param id
     * @return
     */
    public Optional<Users> searchSingle(Long id) {
        Optional<Users> user = userRepository.findById(id);
        return user;
    }
    
    /**
     * 新建
     * 
     * @param role
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public Users creat(Users user) {
        
        user.setPassword(ShiroKit.toSimpleHash(user.getPassword(), user.getName()));
        user.setSalt(ShiroKit.converToSalt(user.getName()));
        user.setCreatDate(DateUtil.format(new Date()));
        user.setUpdateDate(DateUtil.format(new Date()));
        user.setDeleteFlg(false);
        user.setDeptId(null);
        return userRepository.save(user);
    }
    
    /**
     * 更新
     * 
     * @param id
     * @return
     */
    public Users update(Long id, Users user, String flg) {
        Optional<Users> users = userRepository.findById(id);
        if (flg.equals("update")) {
            if (user.getPassword() != null && user.getPassword().length() > 0) {
                user.setPassword(ShiroKit.toSimpleHash(user.getPassword(), users.get().name));
            }
            
            UpdateUtil.copyNonNullProperties(user, users.get());
            users.get().setUpdateDate(DateUtil.format(new Date()));
        } else if (flg.equals("delete")) {
            users.get().setDeleteFlg(true);
            // 删除用户关联的教师
            Criteria<Teacher> criteria = new Criteria<>();
            criteria.add(Restrictions.eq("deleteFlg", false, true));
            criteria.add(Restrictions.eq("userId", id, true));
            List<Teacher> teacherList = teacherRepository.findAll(criteria);
            if (teacherList != null && teacherList.size() > 0) {
                for (Teacher t : teacherList) {
                    t.setDeleteFlg(true);
                    teacherRepository.save(t);
                }
            }
            // 删除用户关联的学员
            Criteria<Student> criteria2 = new Criteria<>();
            criteria2.add(Restrictions.eq("deleteFlg", false, true));
            criteria2.add(Restrictions.eq("userId", id, true));
            List<Student> studentList = studentRepository.findAll(criteria2);
            if (studentList != null && studentList.size() > 0) {
                for (Student s : studentList) {
                    s.setDeleteFlg(true);
                    studentRepository.save(s);
                }
            }
            
        }
        users.get().setUpdateDate(DateUtil.format(new Date()));
        return userRepository.save(users.get());
    }
    
    /**
     * 通过sessionId查询用户
     * 
     * @param id
     * @return
     */
    public Object getUserBySessionId(String id) {
        UserDto userDto = new UserDto();
        Criteria<Users> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("sessionId", id, true));
        List<Users> users = userRepository.findAll(criteria);
        if (null != users && users.size() > 0) {
            userDto = setUser(users.get(0));
        }
        return userDto;
    }
    
    /**
     * 发送验证码
     * 
     * @param users
     * @return
     */
    public Object sendVerifyCode(String id) {
        Optional<Users> user = userRepository.findById(Long.valueOf(id));
        if (null != user) {
            String codes = "";
            codes = this.getRandomCode(6);
            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, 1);
            user.get().setVerifyDate(DateUtil.format(c.getTime()));
            user.get().setVerifyCode(codes);
            return userRepository.save(user.get());
        } else {
            return null;
        }
    }
    
    /**
     * 获取随机数
     * @param number
     * @return
     */
    public String getRandomCode(int number) {
        String codeNum = "";
        int[] code = new int[3];
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int num = random.nextInt(10) + 48;
            int uppercase = random.nextInt(26) + 65;
            int lowercase = random.nextInt(26) + 97;
            code[0] = num;
            code[1] = uppercase;
            code[2] = lowercase;
            codeNum += (char) code[random.nextInt(3)];
        }
        return codeNum;
        
    }
    
    /**
     * 通过sessionId查询用户
     * 
     * @param id
     * @return
     */
    public Object getUserByWechatSessionId(String token) {
        UserDto userDto = new UserDto();
        Criteria<Users> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("wechatSessionId", token, true));
        List<Users> users = userRepository.findAll(criteria);
        if (null != users && users.size() > 0) {
            userDto = setUser(users.get(0));
        }
        return userDto;
    }
    
    /**
     * 赋值到UserDto
     * 
     * @param users
     * @return
     */
    public UserDto setUser(Users users) {
        Users userEntity = users;
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setNikeName(userEntity.getNikeName());
        Role roles = roleRepository.getOne(userEntity.getRoleId());
        JSONObject jsonobject = JSONObject.fromObject(roles.getPermissionList());
        Map<String, String> per = (Map<String, String>) JSONObject.toBean(jsonobject, Map.class);
        userDto.setPermissionMap(per);
        return userDto;
    }
    
    /**
     * 通过课程查学生
     * 
     * @param user
     * @return
     */
    public Map<String, Object> searchTecherByCourseDetailId(UserDto user) {
        
        // 分页使用pageHelper自动完成。
        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        
        List<UserDto> users = userMapper.searchTecherByCourseDetailId(user.getCourseDetailId());
        PageInfo<UserDto> p = new PageInfo<UserDto>(users);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", p.getTotal());
        map.put("results", p.getList());
        map.put("next", null);
        map.put("previous", null);
        
        return map;
    }
    
    /**
     * 通过课程查老师
     * 
     * @param user
     * @return
     */
    public Map<String, Object> searchStrudentByCourseId(UserDto user) {
        
        // 分页使用pageHelper自动完成。
        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        
        List<UserDto> users = userMapper.searchStudentByCourseId(user.getCourseId());
        PageInfo<UserDto> p = new PageInfo<UserDto>(users);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", p.getTotal());
        map.put("results", p.getList());
        map.put("next", null);
        map.put("previous", null);
        
        return map;
    }
    
}
