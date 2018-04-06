package fajnelokalne.demo.repository;

import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    List<Review> findAllByProduct(Product product);
}
