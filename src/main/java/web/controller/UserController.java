package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name) {
        User user = new User();
        user.setName(name);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser (@RequestParam("id") Long id, @RequestParam("name") String name) {
        User user = userService.findByIdUser(id);
        if(user != null) {
            user.setName(name);
            userService.updateUser(user);
        }
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser (@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
