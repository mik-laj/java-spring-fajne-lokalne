package fajnelokalne.demo.filters;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.specifications.CitySpecifications;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CityFilter {

    @Data
    public static class FilterParameter {
        String name;
    }

    @Autowired
    CitySpecifications specifications;

    public Specification<City> apply(FilterParameter filters) {
        if (filters.getName() != null && filters.getName().trim().length() > 0) {
            return specifications.hasNameLike(filters.getName());
        }

        return null;
    }
}
