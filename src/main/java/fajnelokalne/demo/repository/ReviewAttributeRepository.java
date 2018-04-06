package fajnelokalne.demo.repository;

import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.Review;
import fajnelokalne.demo.entity.ReviewAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewAttributeRepository extends JpaRepository<ReviewAttribute, Long>{
}
