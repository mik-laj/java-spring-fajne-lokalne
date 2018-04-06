package fajnelokalne.demo.service;

import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.Review;
import fajnelokalne.demo.entity.ReviewAttribute;
import fajnelokalne.demo.formdata.AddReviewFormData;
import fajnelokalne.demo.formdata.ReviewFormData;
import fajnelokalne.demo.repository.ProductRepository;
import fajnelokalne.demo.repository.ReviewAttributeRepository;
import fajnelokalne.demo.repository.ReviewRepository;
import io.springlets.format.EntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Service
public class ReviewManager implements EntityResolver<Review, Long> {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewAttributeRepository reviewAttributeRepository;

    public List<Review> findAllByProduct(Product product) {
        return reviewRepository.findAllByProduct(product);
    }

    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    public void update(Review review, ReviewFormData formData) {
        Stream<ReviewAttribute> cons = formData.getCons().stream()
                .map(d -> new ReviewAttribute(d, ReviewAttribute.Type.CONS));
        Stream<ReviewAttribute> pros = formData.getPros().stream()
                .map(d -> new ReviewAttribute(d, ReviewAttribute.Type.PROS));

        review.getAttributes().clear();
        Set<ReviewAttribute> currentAttribute = Stream.concat(cons, pros)
                .filter(d -> StringUtils.hasText(d.getContent()))
                .peek(r -> r.setReview(review))
                .collect(toSet());

        currentAttribute.forEach(review::addAttribute);
        reviewAttributeRepository.save(currentAttribute);
        review.setProduct(productRepository.findOne(formData.getProduct().getId()));
        review.setContent(formData.getContent());


        reviewRepository.save(review);
    }

    public Review create(ReviewFormData formData) {

        Review review = new Review();
        review.setProduct(productRepository.findOne(formData.getProduct().getId()));
        Stream<ReviewAttribute> cons = formData.getCons()
                .stream()
                .map(d -> new ReviewAttribute(d, ReviewAttribute.Type.CONS));
        Stream<ReviewAttribute> pros = formData.getPros()
                .stream()
                .map(d -> new ReviewAttribute(d, ReviewAttribute.Type.PROS));
        review.setContent(formData.getContent());
        Stream.concat(cons, pros)
                .filter(d -> StringUtils.hasText(d.getContent()))
                .forEach(review::addAttribute);
        reviewRepository.save(review);
        return review;
    }

    @Override
    public Review findOne(Long aLong) {
        return reviewRepository.findOne(aLong);
    }

    @Override
    public Class<Review> getEntityType() {
        return Review.class;
    }

    @Override
    public Class<Long> getIdType() {
        return Long.class;
    }

    public Review create(Product product, AddReviewFormData formData) {
        Review review = new Review();
        Stream<ReviewAttribute> cons = formData.getCons()
                .stream()
                .map(d -> new ReviewAttribute(d, ReviewAttribute.Type.CONS));
        Stream<ReviewAttribute> pros = formData.getPros()
                .stream()
                .map(d -> new ReviewAttribute(d, ReviewAttribute.Type.PROS));
        review.setContent(formData.getContent());
        Stream.concat(cons, pros)
                .filter(d -> StringUtils.hasText(d.getContent()))
                .forEach(review::addAttribute);
        review.setProduct(product);
        reviewRepository.save(review);
        return review;
    }
}
