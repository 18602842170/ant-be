package com.ant.be.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.CourseDetailDto;
import com.ant.be.entity.Course;
import com.ant.be.entity.CourseDetail;
import com.ant.be.mapper.CourseDetailMapper;
import com.ant.be.repository.CourseDetailRepostory;
import com.ant.be.repository.CourseRepostory;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.UpdateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Component
public class CourseDetailService {
    
    @Autowired
    private CourseDetailRepostory courseDetailRepostory;
    
    @Autowired
    private CourseRepostory courseRepostory;
    
    @Autowired
    private CourseDetailMapper courseDetailMapper;
    
    /**
     * 检索
     * @param id
     * @param name
     * @return
     */
    public Map<String, Object> search(CourseDetailDto courseDetail) {
        // 分页使用pageHelper自动完成。
        PageHelper.startPage(courseDetail.getPageNum(), courseDetail.getPageSize());
        
        List<CourseDetailDto> courses = courseDetailMapper.searchCourseDetail(courseDetail.getId(), courseDetail.getCourseId(), courseDetail.getCourseDay(), courseDetail.getUserId(), courseDetail.getSearchYear(), courseDetail.getSearchMonth(), courseDetail.getSearchDay(), courseDetail.getCourseDayStr(), courseDetail.getIsRollCall(), courseDetail.getIsteacher(), courseDetail.getIsstudent(), courseDetail.getTecherId());
        
        if (courses != null && courses.size() > 0) {
            for (int i = 0; i < courses.size(); i++) {
                courses.get(i).setSerachDate(getStringDate(courses.get(i).getCourseDay()));
            }
        }
        PageInfo<CourseDetailDto> p = new PageInfo<CourseDetailDto>(courses);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", p.getTotal());
        map.put("results", p.getList());
        map.put("next", null);
        map.put("previous", null);
        
        return map;
    }
    
    /**
     * 查询单个
     * @param id
     * @return
     */
    public CourseDetail searchSingle(Long id) {
        Optional<CourseDetail> courseDetail = courseDetailRepostory.findById(id);
        return courseDetail.get();
    }
    
    /**
     * 新建
     * 
     * @param role
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public CourseDetail creat(CourseDetail courseDetail) {
        courseDetail.setDeleteFlg(false);
        courseDetail.setCreatDate(DateUtil.format(new Date()));
        courseDetail.setUpdateDate(DateUtil.format(new Date()));
        return courseDetailRepostory.save(courseDetail);
    }
    
    /**
     * 更新
     * 
     * @param id
     * @return
     */
    public CourseDetail update(Long id, CourseDetail courseDetail, String flg) {
        Optional<CourseDetail> findCourseDetail = courseDetailRepostory.findById(id);
        if (flg.equals("update")) {
            UpdateUtil.copyNonNullProperties(courseDetail, findCourseDetail.get());
            findCourseDetail.get().setUpdateDate(DateUtil.format(new Date()));
        } else if (flg.equals("delete")) {
            findCourseDetail.get().setDeleteFlg(true);
        }
        findCourseDetail.get().setUpdateDate(DateUtil.format(new Date()));
        return courseDetailRepostory.save(findCourseDetail.get());
    }
    
    public static String getStringDate(Date _date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(_date);
        return dateString;
    }
    
    /**
     * 新建课时
     * 
     * @param role
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public Object creatClass(CourseDetailDto courseDetailDto) {
        if ("2".equals(courseDetailDto.getIsRepeat())) {
            CourseDetail courseDetail = new CourseDetail();
            courseDetail.setCourseDay(courseDetailDto.getCourseDay());
            courseDetail.setCourseId(courseDetailDto.getCourseId());
            courseDetail.setCreatDate(DateUtil.format(new Date()));
            courseDetail.setUpdateDate(DateUtil.format(new Date()));
            courseDetail.setDeleteFlg(false);
            courseDetail.setStartTime(courseDetailDto.getStartTime());
            courseDetail.setEndTime(courseDetailDto.getEndTime());
            courseDetail.setUserId(courseDetailDto.getUserId());
            return courseDetailRepostory.save(courseDetail);
            
        } else if ("1".equals(courseDetailDto.getIsRepeat())) {
            int courseNumber = Integer.valueOf(courseDetailDto.getCourseNumber());
            //得到课时数
            Optional<Course> findCourse = courseRepostory.findById(courseDetailDto.getCourseId());
            
            if (findCourse != null) {
                
            }
            
            //判断今天的日期是周几
            List<String> dayList = new ArrayList<>();
            List<String> dayRealList = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int dayLength = 0;
            int dayonweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            
            for (int i = 0; i < courseDetailDto.getDayArray().length; i++) {
                
                if ("true".equals(courseDetailDto.getDayArray()[i].split(":")[1])) {
                    
                    dayLength = dayLength + 1;
                }
            }
            
            for (int i = dayonweek; i < courseDetailDto.getDayArray().length; i++) {
                
                if ("true".equals(courseDetailDto.getDayArray()[i].split(":")[1])) {
                    
                    dayRealList.add(courseDetailDto.getDayArray()[i].split(":")[0]);
                }
                
                if (dayList.size() < courseNumber) {
                    
                    if ("true".equals(courseDetailDto.getDayArray()[i].split(":")[1])) {
                        
                        dayList.add(courseDetailDto.getDayArray()[i].split(":")[0]);
                    }
                }
            }
            
            while (dayList.size() < courseNumber) {
                
                for (int i = 0; i < courseDetailDto.getDayArray().length; i++) {
                    
                    if (dayList.size() < courseNumber) {
                        
                        if ("true".equals(courseDetailDto.getDayArray()[i].split(":")[1])) {
                            
                            dayList.add(courseDetailDto.getDayArray()[i].split(":")[0]);
                            
                        }
                    }
                }
            }
            
            while (dayRealList.size() < dayLength) {
                for (int i = 0; i < courseDetailDto.getDayArray().length; i++) {
                    if (dayRealList.size() < dayLength) {
                        if ("true".equals(courseDetailDto.getDayArray()[i].split(":")[1])) {
                            
                            dayRealList.add(courseDetailDto.getDayArray()[i].split(":")[0]);
                            
                        }
                    }
                }
            }
            
            //根据选中的日期判断日期期间的日期
            Date currentDate = null;
            Date endDate = null;
            if (dayList != null && dayList.size() > 0) {
                int weekonday = Integer.valueOf(dayList.get(0).split(":")[0]);
                //当前日期是否和第一个日期相等
                if (dayonweek == weekonday) {
                    
                    calendar.setTime(new Date());
                    currentDate = calendar.getTime();
                    
                } else if (dayonweek < weekonday) {
                    
                    calendar.setTime(new Date());
                    calendar.add(Calendar.DAY_OF_MONTH, +(weekonday - dayonweek));
                    currentDate = calendar.getTime();
                    
                } else if (dayonweek > weekonday) {
                    
                    int day1 = 6 - dayonweek;
                    int day2 = weekonday + 1;
                    calendar.setTime(new Date());
                    calendar.add(Calendar.DAY_OF_MONTH, +(day1 + day2));
                    currentDate = calendar.getTime();
                }
                
                //根据课时和每个星期的上课数，算出一共需要多少天上完课
                
                if (dayLength != 0) {
                    
                    int classWeek = courseNumber / dayLength;
                    int classDAY = courseNumber % dayLength;
                    int allDay = (courseNumber / dayLength) * 7;
                    calendar.setTime(currentDate);
                    calendar.add(Calendar.DAY_OF_MONTH, +(allDay - 1));
                    endDate = calendar.getTime();
                    calendar.setTime(endDate);
                    int dayonweeks = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                    int j = 0;
                    for (int i = 0; i < dayRealList.size(); i++) {
                        
                        if (Integer.valueOf(dayRealList.get(i)) > dayonweeks) {
                            
                            if (i < classDAY) {
                                
                                calendar.add(Calendar.DAY_OF_MONTH, +(Integer.valueOf(dayRealList.get(i)) - dayonweeks));
                                dayonweeks = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                                j++;
                                
                            }
                        }
                        
                        if (Integer.valueOf(dayRealList.get(i)) < dayonweeks) {
                            
                            if (i < classDAY) {
                                
                                calendar.add(Calendar.DAY_OF_MONTH, +(Integer.valueOf(dayRealList.get(i)) + 1));
                                dayonweeks = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                                j++;
                            }
                        }
                        
                    }
                    endDate = calendar.getTime();
                    
                    //找到开始时间与结束时间之间的所有时间
                    List<Date> dateList = getDateBetweenTwoDate(currentDate, endDate);
                    Iterator<Date> iter = dateList.iterator();
                    
                    for (int i = 0; i < dayList.size(); i++) {
                        
                        int weekday = Integer.valueOf(dayList.get(i));
                        
                        while (iter.hasNext()) {
                            
                            Date temp = iter.next();
                            calendar.setTime(temp);
                            int dateWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                            
                            if (weekday == dateWeek) {
                                
                                //新建课时内容
                                CourseDetail courseDetail = new CourseDetail();
                                
                                courseDetail.setCourseDay(temp);
                                courseDetail.setCourseId(courseDetailDto.getCourseId());
                                courseDetail.setCreatDate(DateUtil.format(new Date()));
                                courseDetail.setUpdateDate(DateUtil.format(new Date()));
                                courseDetail.setDeleteFlg(false);
                                courseDetail.setStartTime(courseDetailDto.getStartTime());
                                courseDetail.setEndTime(courseDetailDto.getEndTime());
                                courseDetail.setUserId(courseDetailDto.getUserId());
                                courseDetailRepostory.save(courseDetail);
                                iter.remove();
                                
                                break;
                                
                            } else {
                                
                                iter.remove();
                                
                            }
                            
                        }
                    }
                    
                }
                
            }
        }
        return null;
    }
    
    private List<Date> getDateBetweenTwoDate(Date currentDate, Date endDate) {
        
        if (currentDate.getTime() == endDate.getTime()) {
            return null;
        }
        
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(currentDate);//把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        boolean bContinue = true;
        while (bContinue) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            
            if (endDate.after(cal.getTime())) {
                
                lDate.add(cal.getTime());
            } else {
                
                break;
            }
        }
        
        lDate.add(endDate);//把结束时间加入集合
        return lDate;
    }
    
    /**
     * 移除具体课时
     * @param id
     * @param name
     * @return
     */
    public Object removeByDate(CourseDetailDto courseDetailDto) {
        if (courseDetailDto.getCourseDay() != null) {
            courseDetailDto.setCourseDayStr(DateUtil.format(courseDetailDto.getCourseDay(), "yyyy-MM-dd"));
        }
        
        List<CourseDetailDto> courses = courseDetailMapper.searchCourseDetail(null, courseDetailDto.getCourseId(), courseDetailDto.getCourseDay(), null, courseDetailDto.getSearchYear(), courseDetailDto.getSearchMonth(), courseDetailDto.getSearchDay(), courseDetailDto.getCourseDayStr(), courseDetailDto.getIsRollCall(), courseDetailDto.getIsteacher(), courseDetailDto.getIsstudent(), courseDetailDto.getTecherId());
        
        if (courses != null && courses.size() > 0) {
            
            this.update(courses.get(0).getId(), courses.get(0), "delete");
        }
        return null;
    }
}
