package controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

import java.util.List;

@PropertySource("classpath:DB.properties")
@Controller
public class AppController {
    private static final Logger LOG = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private Environment env;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        LOG.info("Inside login or welcome page!");
        return "login";
    }
/*
    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    @Transactional(readOnly = true)
    public String auth(@ModelAttribute("login") String login, @ModelAttribute("password") String password) {
        LOG.info("Inside authorization! User info \nlogin: " + login + "\npassword: " + password);
        if (userService.checkAuth(login, password)) {
            return "redirect:/admin/all";
        } else if (login.equals(env.getProperty("web.admin")) && password.equals(env.getProperty("web.password"))) {
            userService.addUser(new User("Root admin", "Root admin", "admin", "admin", 0L,"admin"));
            return "redirect:/user";
        }
        return "redirect:/login";
    }*/

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("firstName") String firstName,
                      @ModelAttribute("lastName") String lastName,
                      @ModelAttribute("phoneNumber") String phoneNumber,
                      @ModelAttribute("role") String role,
                      @ModelAttribute("login") String login,
                      @ModelAttribute("password") String password) {
        LOG.info("Inside add!");
        userService.addUser(new User(firstName, lastName, login, password, Long.parseLong(phoneNumber), role));
        return "redirect:/admin/all";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(@ModelAttribute("user") User user) {
        return "user";
    }

    @RequestMapping(value = "/admin/all", method = RequestMethod.GET)
    public String allUser(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("listUser", users);
        return "allUsers";
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public String editUserPage(@ModelAttribute("id") String id, ModelMap model) {
        model.addAttribute("id", id);
        return "editUsers";
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("id") String id,
                           @ModelAttribute("firstName") String firstName,
                           @ModelAttribute("lastName") String lastName,
                           @ModelAttribute("phoneNumber") String phoneNumber,
                           @ModelAttribute("role") String role,
                           @ModelAttribute("login") String login,
                           @ModelAttribute("password") String password) {
        userService.updateUser(id, firstName, lastName, phoneNumber, role, login, password);
        return "redirect:/admin/all";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String delUser(@ModelAttribute("delId") String id) {
        userService.delUser(id);
        return "redirect:/admin/all";
    }
}
