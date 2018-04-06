package fajnelokalne.demo.repository;

import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.User;
import fajnelokalne.demo.entity.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsBycreatedByAndProduct(User user, Product product);
}