package fajnelokalne.demo.repository;

import fajnelokalne.demo.entity.Company;
import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.Tag;
import io.springlets.data.domain.GlobalSearch;
import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>
{
    Page<Product> findAll(Pageable pageable);

    Page<Product> findByNameLike(String name, Pageable pageable);

    Page<Product> findAllByTags(Tag tag, Pageable pageable);

    Page<Product> findAllByCompany(Company company, Pageable pageable);
}
