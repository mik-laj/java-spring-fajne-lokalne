package fajnelokalne.demo.repository;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.entity.Product;
import io.springlets.format.EntityResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    Page<City> findByNameLike(String name, Pageable pageable);
}
