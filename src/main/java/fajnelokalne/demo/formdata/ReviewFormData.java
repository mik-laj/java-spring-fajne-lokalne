package fajnelokalne.demo.formdata;

import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.Review;
import fajnelokalne.demo.entity.ReviewAttribute;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
public class ReviewFormData {
    ChoiceItem product;
    String content;
    List<String> cons = new ArrayList<>();
    List<String> pros = new ArrayList<>();

    public ReviewFormData(Review review) {
        Product product = review.getProduct();
        Long product_id = product.getId();
        String product_label = product.getName();
        this.product = new ChoiceItem(product_id, product_label);
        this.content = review.getContent();
        this.cons = review.getCons().stream().map(ReviewAttribute::getContent).collect(toList());
        this.pros = review.getPros().stream().map(ReviewAttribute::getContent).collect(toList());
    }
}
