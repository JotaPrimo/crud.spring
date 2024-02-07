package br.com.jotasantos.crud.spring.web.controllers;

import br.com.jotasantos.crud.spring.entities.User;
import br.com.jotasantos.crud.spring.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Optional;

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

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());

        return "/users/create";
    }

    @GetMapping("/{id}/show")
    public String show(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            return "/users/show";
        }

        redirectAttributes.addFlashAttribute("msgDanger", "User not found");

        return "/users";
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

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());

            return "/users/edit";
        }

        redirectAttributes.addFlashAttribute("msgDanger", "User not found");

        return "/users/create";
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute("user") @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msgDanger", "Ocorreu um erro");

            return "redirect:/users";
        }

        userService.update(id, user);
        redirectAttributes.addFlashAttribute("msgSuccess", "User updated with succesfull");

        return "redirect:/users";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent()) {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("msgSuccess", "User deleted with a success");

            return "redirect:/users";
        }

        redirectAttributes.addFlashAttribute("msgDanger", "User not found");

        return "redirect:/users";
    }

}
