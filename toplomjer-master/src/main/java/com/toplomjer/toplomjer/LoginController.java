package com.toplomjer.toplomjer;

import com.toplomjer.toplomjer.model.User;
import com.toplomjer.toplomjer.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String showLogin(Model model) {
        return "login.html";
    }

    @GetMapping("/login")
    public String processLogin(Model model, String username, String password) {


        User currUser = userRepository.findByUsernameAndPassword(username, password);

        if ( currUser != null) {
            if (currUser.getPermissionLevel() == 1) {
                return "redirect:/doctor-dashboard?id=" + currUser.getId();
            } else {
                return "redirect:/form-01?id=" + currUser.getId() + "&password=" + password;
            }
        } else {
            model.addAttribute ("warningMessage", "Pogresni unos korisničkog imena i/ili lozinke!");
            return "login.html";
        }

    }
}
