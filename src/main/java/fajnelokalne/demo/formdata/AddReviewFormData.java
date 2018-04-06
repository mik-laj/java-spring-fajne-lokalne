package fajnelokalne.demo.formdata;

import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.Review;
import fajnelokalne.demo.entity.ReviewAttribute;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
public class AddReviewFormData {
    ChoiceItem product;
    @NotNull
    String content;
    List<String> cons = new ArrayList<>();
    List<String> pros = new ArrayList<>();

    public AddReviewFormData(Review review) {
        this.content = review.getContent();
        this.cons = review.getCons().stream().map(ReviewAttribute::getContent).collect(toList());
        this.pros = review.getPros().stream().map(ReviewAttribute::getContent).collect(toList());
    }
}
