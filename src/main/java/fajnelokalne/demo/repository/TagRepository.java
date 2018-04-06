package fajnelokalne.demo.repository;

import fajnelokalne.demo.entity.City;
import fajnelokalne.demo.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findOneByName(String name);
    Page<Tag> findAllByNameLike(String name, Pageable pageable);
}
