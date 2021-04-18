package ru.omsu.imit.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.omsu.imit.web.utils.WebUtils;

import java.security.Principal;

@Controller
@Slf4j
public class MainController {

    @GetMapping(value = { "/", "/welcome" })
    public String welcomePage(Model model) {
        log.debug("/welcome page is accessed");
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, Principal principal) {
        log.debug("/admin page is accessed");
        addUserInfoAttribute(model, principal);
        return "adminPage";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        log.debug("/login page is accessed");
        return "loginPage";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        log.debug("/logoutSuccessful page is accessed");
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {
        log.debug("/userInfo page is accessed");
        addUserInfoAttribute(model, principal);
        return "userInfoPage";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        log.debug("/403 page is accessed");
        if (principal != null) {
            addUserInfoAttribute(model, principal);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }

    private void addUserInfoAttribute(Model model, Principal principal) {
        User enteredUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.getUserInfo(enteredUser);
        model.addAttribute("userInfo", userInfo);
    }
}
