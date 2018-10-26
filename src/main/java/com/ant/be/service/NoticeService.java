package com.ant.be.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.be.dto.NoticeDto;
import com.ant.be.entity.CourseAllot;
import com.ant.be.entity.Notice;
import com.ant.be.jpa.Criteria;
import com.ant.be.jpa.factory.Restrictions;
import com.ant.be.mapper.NoticeMapper;
import com.ant.be.repository.CourseAllotRepository;
import com.ant.be.repository.NoticeRepository;
import com.ant.be.utils.DateUtil;
import com.ant.be.utils.UpdateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Component
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepostory;

	@Autowired
	private NoticeMapper noticeMapper;

	@Autowired
	private CourseAllotRepository courseAllotRepository;

	/**
	 * 检索
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public Map<String, Object> search(NoticeDto noticeDto) {
		// 分页使用pageHelper自动完成。
		PageHelper.startPage(noticeDto.getPageNum(), noticeDto.getPageSize());

		if (!("").equals(noticeDto.getNoticeTitle()) && null != noticeDto.getNoticeTitle()) {
			noticeDto.setNoticeTitle("%" + noticeDto.getNoticeTitle() + "%");
		}

		if (!("").equals(noticeDto.getPublishName()) && null != noticeDto.getPublishName()) {
			noticeDto.setPublishName("%" + noticeDto.getPublishName() + "%");
		}
		String teacherNotice = "";
		String schoolNotice="";
		if (!("").equals(noticeDto.getTeacherNoticeTargets()) && null != noticeDto.getTeacherNoticeTargets()) {
			// 人员分类为学员
			if ("2".equals(noticeDto.getTeacherNoticeTargets())) {
				Criteria<CourseAllot> criteria = new Criteria<>();
				criteria.add(Restrictions.eq("userType", noticeDto.getTeacherNoticeTargets(), true));
				criteria.add(Restrictions.eq("userId", noticeDto.getUserOfStuId(), true));
				criteria.add(Restrictions.eq("deleteFlg", false, true));
				List<CourseAllot> courseAllots = courseAllotRepository.findAll(criteria);
//				schoolNotice = "-1";
				if (courseAllots.size() > 0) {
					for (int i = 0; i < courseAllots.size(); i++) {
						if (i == courseAllots.size()-1) {
							schoolNotice += courseAllots.get(i).getCourseId();
						} else {
							schoolNotice += courseAllots.get(i).getCourseId() + ",";
						}
					}
				}
			}
		}else {
			schoolNotice=noticeDto.getSchoolNoticeTargets();
		}

		// 通知对象
		List<NoticeDto> notices = noticeMapper.searchNoticeList(schoolNotice,
				noticeDto.getNoticeTitle(), noticeDto.getPublishName(), noticeDto.getSearchYear(),
				noticeDto.getSearchMonth(), noticeDto.getSearchDay());
		if (notices != null && notices.size() > 0) {
			for (int i = 0; i < notices.size(); i++) {
				if (notices.get(i).getCreatDate() != null) {
					notices.get(i).setNoticeDateStr(getStringDate(notices.get(i).getCreatDate()));
				} else {
					notices.get(i).setNoticeDateStr("");
				}

			}
		}
		PageInfo<NoticeDto> p = new PageInfo<NoticeDto>(notices);

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
	public Notice searchSingle(Long id) {
		Optional<Notice> notice = noticeRepostory.findById(id);
		return notice.get();
	}

	/**
	 * 新建
	 * 
	 * @param role
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public Notice creat(Notice notice) throws Exception {
		notice.setUserId(notice.getUserId());
		notice.setNoticeTarget(notice.getNoticeTarget());
		notice.setDeleteFlg(false);
		notice.setCreatDate(DateUtil.format(new Date()));
		notice.setUpdateDate(DateUtil.format(new Date()));
		return noticeRepostory.save(notice);
	}

	/**
	 * 更新
	 * 
	 * @param id
	 * @return
	 */
	public Notice update(Long id, Notice notice, String flg) {
		Optional<Notice> findNotice = noticeRepostory.findById(id);
		if (flg.equals("update")) {
			UpdateUtil.copyNonNullProperties(notice, findNotice.get());
			findNotice.get().setUpdateDate(DateUtil.format(new Date()));
		} else if (flg.equals("delete")) {
			findNotice.get().setDeleteFlg(true);
		}
		findNotice.get().setUpdateDate(DateUtil.format(new Date()));
		return noticeRepostory.save(findNotice.get());
	}

	public static String getStringDate(Date _date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(_date);
		return dateString;
	}

}
