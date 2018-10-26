package com.ant.be.dto;

import java.util.Map;

import com.ant.be.entity.Users;

public class UserDto extends Users {
    private static final long serialVersionUID = 1L;
    // 当前页
    private int pageNum;
    // 每页的数量
    private int pageSize;
    
    private String roleName;
    
    private String deptName;
    
    private String searchTecherOrStrudentByCourseId;
    
    private Long courseId;
    
    private Long courseDetailId;
    
    private Map<String, String> permissionMap;
    
    private Object permissionList;
    
    // 用户对应在教师表中的id
    private Long teacherId;
    
    // 用户对应在学员表中的id
    private Long studentId;
    
    public Long getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    
    public String getSearchTecherOrStrudentByCourseId() {
        return searchTecherOrStrudentByCourseId;
    }
    
    public void setSearchTecherOrStrudentByCourseId(String searchTecherOrStrudentByCourseId) {
        this.searchTecherOrStrudentByCourseId = searchTecherOrStrudentByCourseId;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getDeptName() {
        return deptName;
    }
    
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
    public Map<String, String> getPermissionMap() {
        return permissionMap;
    }
    
    public void setPermissionMap(Map<String, String> permissionMap) {
        this.permissionMap = permissionMap;
    }
    
    public int getPageNum() {
        return pageNum;
    }
    
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public Object getPermissionList() {
        return permissionList;
    }
    
    public void setPermissionList(Object permissionList) {
        this.permissionList = permissionList;
    }
    
    public Long getTeacherId() {
        return teacherId;
    }
    
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
    
    public Long getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public Long getCourseDetailId() {
        return courseDetailId;
    }
    
    public void setCourseDetailId(Long courseDetailId) {
        this.courseDetailId = courseDetailId;
    }
    
}
