package fajnelokalne.demo.repository;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
    Page<Company> findByNameLike(String name, Pageable pageable);

    Page<Company> findAllByCity(City city, Pageable pageable);
}
