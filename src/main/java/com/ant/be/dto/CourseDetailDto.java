package com.ant.be.dto;

import com.ant.be.entity.CourseDetail;

public class CourseDetailDto extends CourseDetail {
    
    private static final long serialVersionUID = 1L;
    
    // 是查询老师
    private Long isteacher;
    
    // 是查询学生
    private Long isstudent;
    
    // 课程名称
    private String name;
    
    // 课时数
    private String courseNumber;
    
    // 检索条件
    private Long userId;
    
    // 检索条件
    private int searchYear;
    
    // 检索条件
    private int searchMonth;
    
    // 检索条件
    private int searchDay;
    
    // 数据库中取出的格式化为yyyy-mm-dd类型的课时日期
    private String courseDayStr;
    
    private String serachDate;
    
    private Long techerId;
    
    public String getSerachDate() {
        return serachDate;
    }
    
    public void setSerachDate(String serachDate) {
        this.serachDate = serachDate;
    }
    
    // 当前页
    private int pageNum;
    //是否重复
    private String isRepeat;
    
    private String[] dayArray;
    
    public String getCourseNumber() {
        return courseNumber;
    }
    
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }
    
    public String[] getDayArray() {
        return dayArray;
    }
    
    public void setDayArray(String[] dayArray) {
        this.dayArray = dayArray;
    }
    
    public String getIsRepeat() {
        return isRepeat;
    }
    
    public void setIsRepeat(String isRepeat) {
        this.isRepeat = isRepeat;
    }
    
    // 每页的数量
    private int pageSize;
    
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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public int getSearchYear() {
        return searchYear;
    }
    
    public void setSearchYear(int searchYear) {
        this.searchYear = searchYear;
    }
    
    public int getSearchMonth() {
        return searchMonth;
    }
    
    public void setSearchMonth(int searchMonth) {
        this.searchMonth = searchMonth;
    }
    
    public int getSearchDay() {
        return searchDay;
    }
    
    public void setSearchDay(int searchDay) {
        this.searchDay = searchDay;
    }
    
    public String getCourseDayStr() {
        return courseDayStr;
    }
    
    public void setCourseDayStr(String courseDayStr) {
        this.courseDayStr = courseDayStr;
    }
    
    public Long getIsteacher() {
        return isteacher;
    }
    
    public void setIsteacher(Long isteacher) {
        this.isteacher = isteacher;
    }
    
    public Long getIsstudent() {
        return isstudent;
    }
    
    public void setIsstudent(Long isstudent) {
        this.isstudent = isstudent;
    }
    
    public Long getTecherId() {
        return techerId;
    }
    
    public void setTecherId(Long techerId) {
        this.techerId = techerId;
    }
    
}
