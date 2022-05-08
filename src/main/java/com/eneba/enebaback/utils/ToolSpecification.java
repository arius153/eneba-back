package com.eneba.enebaback.utils;

import com.eneba.enebaback.dto.ToolFilterModel;
import com.eneba.enebaback.entities.Tool;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ToolSpecification implements Specification<Tool> {

    private final ToolFilterModel filterModel;

    public ToolSpecification(ToolFilterModel toolFilterModel) {
        this.filterModel = toolFilterModel;
    }

    @Override
    public Predicate toPredicate(Root<Tool> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filterModel.getMaxPrice() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("price"), filterModel.getMaxPrice()));
        }

        if (filterModel.getMinPrice() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("price"), filterModel.getMinPrice()));
        }

        List<Predicate> categoryPredicates = new ArrayList<>();
        if (filterModel.getCategory() != null && filterModel.getCategory().size() > 0) {
            for (Integer category: filterModel.getCategory()) {
                categoryPredicates.add(builder.equal(root.get("toolCategory"), category));
            }

            Predicate predicate = categoryPredicates.get(0);

            for (Predicate p : categoryPredicates) {
                predicate = builder.or(predicate, p);
            }

            predicates.add(predicate);
        }

        Predicate predicate = predicates.get(0);
        for (Predicate p : predicates) {
            predicate = builder.and(predicate, p);
        }

        return predicate;
    }
}
