package fajnelokalne.demo.filters;

import fajnelokalne.demo.entity.User;
import fajnelokalne.demo.specifications.UserSpecifications;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserFilter {

    @Data
    public static class FilterParameter {
        String username;
    }

    @Autowired
    UserSpecifications specifications;

    public Specification<User> apply(FilterParameter filters) {
        if (filters.getUsername() != null && filters.getUsername().trim().length() > 0) {
            return specifications.hasUsernameLike(filters.getUsername());
        }

        return null;
    }
}
