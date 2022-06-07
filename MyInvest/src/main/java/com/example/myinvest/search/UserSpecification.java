package com.example.myinvest.search;

import com.example.myinvest.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
@AllArgsConstructor
public class UserSpecification implements Specification<UserEntity> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(
            Root<UserEntity> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder builder) {

        return switch (criteria.getOperation()) {
            case EQUALITY -> builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION -> builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN -> builder.greaterThan(root.<String>get(
                    criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN -> builder.lessThan(root.<String>get(
                    criteria.getKey()), criteria.getValue().toString());
            case LIKE -> builder.like(root.<String>get(
                    criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH -> builder.like(root.<String>get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH -> builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS -> builder.like(root.<String>get(
                    criteria.getKey()), "%" + criteria.getValue() + "%");
        };
    }
}