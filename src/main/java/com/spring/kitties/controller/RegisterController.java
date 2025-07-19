package com.spring.kitties.controller;

import com.spring.kitties.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/accounts")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String home() {
        return "redirect:/account";
    }

//    @PostMapping("/register")
//    public String registerUser(@RequestParam String firstname,
//       @RequestParam String lastname,
//       @RequestParam String username,
//       @RequestParam String email,
//       @RequestParam String password,
//       @RequestParam String city,
//       RedirectAttributes redirectAttributes)
//    {
//        try {
//            userService.addUser(new User(firstname, lastname, username, email, password, city));
//            return "redirect:/login";
//        } catch (EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
//            redirectAttributes.addAttribute("errorMessage", e.getMessage());
//            return "register";
//        }
//    }
}