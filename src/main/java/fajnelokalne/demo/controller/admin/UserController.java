package fajnelokalne.demo.controller.admin;

import fajnelokalne.demo.entity.User;
import fajnelokalne.demo.filters.UserFilter;
import fajnelokalne.demo.formdata.CreateUserFormData;
import fajnelokalne.demo.formdata.EditUserFormData;
import fajnelokalne.demo.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserManager userManager;

    @GetMapping("")
    String list(
            @ModelAttribute("filter") UserFilter.FilterParameter filter,
            Model model,
            Pageable pageable
    ) {
        model.addAttribute("objectsList", userManager.findAll(filter, pageable));
        return "admin/user/list";
    }

    @GetMapping("/{id}")
    String view(
            @PathVariable("id") User entity,
            Model model
    ) {
        model.addAttribute("object", entity);
        return "admin/user/view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    String add(
            @ModelAttribute("formData") CreateUserFormData formData
    ) {
        return "admin/user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String add(
            @ModelAttribute("formData") @Valid CreateUserFormData formData,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/user/add";
        }

        User entity = userManager.create(formData);

        return String.format("redirect:/admin/user/%d/", entity.getId());
    }

    @GetMapping("/{id}/edit")
    String edit(
            @PathVariable("id") User user,
            Model model
    ) {
        model.addAttribute("formData", new EditUserFormData(user));
        model.addAttribute("object", user);
        return "admin/user/edit";
    }

    @PostMapping("/{id}/edit")
    String edit(
            @ModelAttribute("formData") EditUserFormData formData,
            BindingResult bindingResult,
            @PathVariable("id") User entity,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/user/edit";
        }
        userManager.update(entity, formData);
        model.addAttribute("object", entity);
//        companyManager.save(entity);
        return String.format("redirect:/admin/user/%d/", entity.getId());
    }

    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    String delete(
            HttpServletRequest request,
            @PathVariable("id") User entity,
            Model model
    ) {
        boolean isPost = request.getMethod().equals("POST");
        if (isPost) {
            userManager.delete(entity);
            // Add Toast
            return "redirect:/admin/user/";
        }
        model.addAttribute("object", entity);
        return "admin/user/delete";
    }


}
