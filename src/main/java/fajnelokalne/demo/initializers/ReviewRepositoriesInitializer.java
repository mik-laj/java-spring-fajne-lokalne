package fajnelokalne.demo.initializers;

import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.Review;
import fajnelokalne.demo.entity.ReviewAttribute;
import fajnelokalne.demo.entity.User;
import fajnelokalne.demo.repository.ProductRepository;
import fajnelokalne.demo.repository.ReviewRepository;
import fajnelokalne.demo.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Component
@Profile("!production")
public class ReviewRepositoriesInitializer  {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public void run() throws Exception {
        if(reviewRepository.count() == 0) {
            List<Product> products = productRepository.findAll();
            List<User> user = userRepository.findAll();

            for(int i = 0; i < 10; i++) {
                Review review = new Review();
                review.setCreatedBy(user.get(0));
                review.setCreationDate(new Date());
                review.setLastModifiedBy(user.get(0));
                review.setLastModifiedDate(new Date());
                review.setProduct(products.get((int) (Math.random() * products.size())));
                review.setContent("Lorem ipsum");
                for(int j = 0; j < 10; j++) {
                    ReviewAttribute attribute = new ReviewAttribute();
                    attribute.setContent("Lorem #" + j);
                    attribute.setReview(review);
                    attribute.setType(j %2 == 0?ReviewAttribute.Type.CONS :ReviewAttribute.Type.PROS);
                    review.addAttribute(attribute);
                }
                reviewRepository.save(review);
            }
        }
    }
}
