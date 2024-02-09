package com.chernov.exampleproject.repositoy.spec;

import com.chernov.exampleproject.model.dto.UserFilterDto;
import com.chernov.exampleproject.model.entity.EmailData;
import com.chernov.exampleproject.model.entity.PhoneData;
import com.chernov.exampleproject.model.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
public class UserFilterSpecification implements Specification<User> {

    private final UserFilterDto userFilterDto;

    @Override
    public Predicate toPredicate(@NonNull Root<User> user,
                                 @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        boolean countedQuery = query.getResultType() != Long.class;
        Join<User, PhoneData> phoneDataJoin = getPhoneDataJoin(user, countedQuery);
        Join<User, EmailData> emailDataJoin = getEmailDataJoin(user, countedQuery);

        String name = userFilterDto.getName();
        if (name != null && !name.isBlank()) {
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(user.get("name")), "%" + name.toLowerCase() + "%");
            predicates.add(namePredicate);
        }

        LocalDate dateOfBirth = userFilterDto.getDateOfBirth();
        if (dateOfBirth != null) {
            Predicate dateOfBirthPredicate = criteriaBuilder.greaterThan(user.get("dateOfBirth"), dateOfBirth);
            predicates.add(dateOfBirthPredicate);
        }

        String phone = userFilterDto.getPhone();
        if (phone != null && !phone.isBlank()) {
            Predicate phonePredicate = criteriaBuilder.equal(phoneDataJoin.get("phone"), phone);
            predicates.add(phonePredicate);
        }

        String email = userFilterDto.getEmail();
        if (email != null && !email.isBlank()) {
            Predicate emailPredicate = criteriaBuilder.equal(emailDataJoin.get("email"), email);
            predicates.add(emailPredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private static <X, Z> Join<X, Z> castToJoin(Fetch<X, Z> fetch) {
        return (Join<X, Z>) fetch;
    }

    private Join<User, PhoneData> getPhoneDataJoin(Root<User> user, boolean countedQuery) {
        return countedQuery
                ? user.join("phoneData", JoinType.LEFT)
                : castToJoin(user.fetch("phoneData", JoinType.LEFT));
    }

    private Join<User, EmailData> getEmailDataJoin(Root<User> user, boolean countedQuery) {
        return countedQuery
                ? user.join("emailData", JoinType.LEFT)
                : castToJoin(user.fetch("emailData", JoinType.LEFT));
    }
}
