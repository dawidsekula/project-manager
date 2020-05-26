package site.transcendence.projectmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import site.transcendence.projectmanager.model.user.UserDto;
import site.transcendence.projectmanager.model.user.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public String getUser(@PathVariable("userId") Long userId, Model model){
        UserDto user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "user-details";
    }

}
