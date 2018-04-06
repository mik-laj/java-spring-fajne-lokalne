package fajnelokalne.demo.repository;

import fajnelokalne.demo.entity.Image;
import fajnelokalne.demo.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}