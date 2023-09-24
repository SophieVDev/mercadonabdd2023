package fr.mercadona.mercadona.controller;

import fr.mercadona.mercadona.model.User;
import fr.mercadona.mercadona.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute ("registerRequest", new User());
        return "register_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        System.out.println("register request:" + user );
        User registeredUser = userService.registerUser(user.getLogin(), user.getPassword(),user.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }


    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new User());
        return "login_user";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model){
        System.out.println("login request:" + user );
        User authenticated = userService.authenticate(user.getLogin(), user.getPassword());
        if(authenticated !=null){
            model.addAttribute("userLogin",authenticated.getLogin());
            return "redirect:/produits/intranet";
        }else{
            return "error_page";
        }
    }


    @GetMapping("/login/intranet")
    public String getProductIntranetPage(Model model){
        return "redirect:/products/intranet";
    }


    @GetMapping("/erreur")
    public String getErrorPage() {
        return "error_page";
    }

}
