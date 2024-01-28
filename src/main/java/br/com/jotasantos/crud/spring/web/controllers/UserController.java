package br.com.jotasantos.crud.spring.web.controllers;

import br.com.jotasantos.crud.spring.entities.User;
import br.com.jotasantos.crud.spring.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.list());

        return "users/index";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msgDanger", "Ocorreu um erro");

            return "redirect:/users";
        }

        redirectAttributes.addFlashAttribute("msgSuccess", "User created with succesfull");
        userService.store(user);

        return "redirect:/users";
    }
}
