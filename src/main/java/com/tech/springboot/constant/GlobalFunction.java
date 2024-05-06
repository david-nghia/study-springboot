package com.tech.springboot.constant;

import com.tech.springboot.mapper.UserMapper;
import com.tech.springboot.model.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalFunction {

    public static <T> List<?> filter(EntityManager entityManager, Class<T> entityClass,
                              Integer offset, Integer limit,
                                     List<String> sort, List<String> search) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
        Root<T> root = query.from(entityClass);

        List<Predicate> predicates = new ArrayList<>();

        if (search!=null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|>|<)(.*)");
            for (String param : search) {
                Matcher matcher = pattern.matcher(param);
                if (matcher.find()) {
                    predicates.add(criteriaBuilder.like(root.get(matcher.group(1)), "%" + matcher.group(3) + "%"));
                }
            }
        }

        if (sort!=null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|>|<)(.*)");
            for (String param : sort) {
                Matcher matcher = pattern.matcher(param);
                if (matcher.find()) {
                    if (matcher.group(3).equalsIgnoreCase("ASC")) {
                        query.orderBy(criteriaBuilder.asc(root.get(matcher.group(1))));
                    } else if (matcher.group(3).equalsIgnoreCase("DESC")) {
                        query.orderBy(criteriaBuilder.desc(root.get(matcher.group(1))));
                    }
                }
            }
        }

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        query.where(finalPredicate);

        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(limit);

        List<T> list = typedQuery.getResultList();
        return list;
    }

}
