package fajnelokalne.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping("/admin")
    public String adminHome(){
        return "redirect:/admin/dashboard/";
    }

    @RequestMapping("/admin/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

}
