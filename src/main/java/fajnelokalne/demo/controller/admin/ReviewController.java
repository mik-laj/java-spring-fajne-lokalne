package fajnelokalne.demo.controller.admin;

import fajnelokalne.demo.entity.Review;
import fajnelokalne.demo.formdata.ReviewFormData;
import fajnelokalne.demo.service.ReviewManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/review")
public class ReviewController {
    @Autowired
    ReviewManager reviewManager;

    @GetMapping()
    String list(
            Model model,
            Pageable pageable
    ) {
        model.addAttribute("objectsList", reviewManager.findAll(pageable));
        return "admin/review/list";
    }


    @GetMapping("/{id}")
    String view(
            @PathVariable("id") Review review,
            Model model
    ) {
        model.addAttribute("object", review);
        return "admin/review/view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    String add(
            @ModelAttribute("formData") ReviewFormData formData
    ) {
        return "admin/review/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String add(
            @ModelAttribute("formData") @Valid ReviewFormData formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/review/add";
        }
        Review review = reviewManager.create(formData);
        return String.format("redirect:/admin/review/%d/", review.getId());
    }

    @GetMapping("/{id}/edit")
    String edit(
            @PathVariable("id") Review review,
            Model model
    ) {
        model.addAttribute("formData", new ReviewFormData(review));
        model.addAttribute("object", review);
        return "admin/review/edit";
    }

    @PostMapping("/{id}/edit")
    String edit(
            @ModelAttribute("formData") ReviewFormData formData,
            BindingResult bindingResult,
            @PathVariable("id") Review review,
            Model model
    ) {
        model.addAttribute("object", review);
        reviewManager.update(review, formData);
        if (bindingResult.hasErrors()) {
            return "admin/review/edit";
        }

        return String.format("redirect:/admin/review/%d/", review.getId());
    }

    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    String delete(
            HttpServletRequest request,
            @PathVariable("id") Review review,
            Model model
    ) {
        boolean isPost = request.getMethod().equals("POST");
        if (isPost) {
            reviewManager.delete(review);
            // TODO: Add Toast
            return "redirect:/admin/review/";
        }
        model.addAttribute("object", review);
        return "admin/review/delete";
    }

}
