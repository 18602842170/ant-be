package com.ant.be.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * 定义查询容器
 * @author chenzhuoqi
 *
 * @param <T>
 */
public class Criteria<T> implements Specification<T> {
	private static final long serialVersionUID = 1L;
	private List<Criterion> criterions = new ArrayList<>();

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (!criterions.isEmpty()) {
			List<Predicate> predicates = new ArrayList<>();
			for (Criterion c : criterions) {
				predicates.add(c.toPredicate(root, query, criteriaBuilder));
			}
			// 将所有条件用 and 联合起来
			if (predicates.size() > 0) {
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}
		return criteriaBuilder.conjunction();
	}

    /**
     * 增加简单条件表达式
     *
     * @Methods Name add
     * @Create In 2012-2-8 By lee
     */
    public void add(Criterion criterion) {
        if (criterion != null) {
            criterions.add(criterion);
        }
    }

}
