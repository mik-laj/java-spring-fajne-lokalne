package fajnelokalne.demo.controller.app;

import fajnelokalne.demo.entity.Product;
import fajnelokalne.demo.entity.Review;
import fajnelokalne.demo.filters.ProductFilter;
import fajnelokalne.demo.formdata.AddReviewFormData;
import fajnelokalne.demo.service.ProductManager;
import fajnelokalne.demo.service.ReviewManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class AppProductController {
    @Autowired
    ProductManager productManager;

    @Autowired
    ReviewManager reviewManager;

    @GetMapping("")
    String list(
            @ModelAttribute("filter") ProductFilter.FilterParameter filter,
            Model model,
            Pageable pageable
    ) {
        model.addAttribute("objectsList", productManager.findAll(filter, pageable));
        return "product/list";
    }


    @GetMapping("/{id}/")
    String view(
            @PathVariable("id") Product product,
            Model model
    ) {
        model.addAttribute("object", product);
        model.addAttribute("formData", new AddReviewFormData());
        model.addAttribute("reviews", reviewManager.findAllByProduct(product));
        return "product/view";
    }

    @RequestMapping(value = "/{id}/add-review", method = RequestMethod.POST)
    String add(
            @PathVariable("id") @ModelAttribute("object") Product product,
            @ModelAttribute("formData") @Valid AddReviewFormData formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "view/add";
        }
        Review review = reviewManager.create(product, formData);
        return String.format("redirect:/product/%d/#review-%s", product.getId(), review.getId());
    }

}
