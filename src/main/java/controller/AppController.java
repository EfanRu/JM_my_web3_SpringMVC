package controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String login(ModelMap model) {
        LOG.info("Inside login or welcome page!");
        return "login";
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public String auth(@ModelAttribute("login") String login, @ModelAttribute("password") String password) {
        LOG.info("Inside authorization! User info \nlogin: " + login + "\npassword: " + password);
        if (userService.checkAuth(login, password)) {
            return "redirect:/user";
        } else if (login.equals(env.getProperty("web.admin")) && password.equals(env.getProperty("web.password"))) {
            userService.addUser(new User("Root admin", "Root admin", "admin", "admin", 0L,"admin"));
            return "redirect:/user";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String add(ModelMap model) {
        LOG.info("Inside add!");
        return "allUsers";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(ModelMap model) {
        return "user";
    }

    @RequestMapping(value = "/admin/all", method = RequestMethod.GET)
    public String allUser(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("listUser", users);
        return "allUsers";
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public String editUser(ModelMap model) {
        return "editUsers";
    }

//
//    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
////    @GetMapping("/")
//    public String login(ModelMap model) {
//        model.addAttribute("title", "Login page");
//        model.addAttribute("message", "Please log in app");
//        return "index";
//    }

/*    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login page");
        model.addObject("message", "Please log in app");
        model.setViewName("login");
        return model;
    }*/
}
