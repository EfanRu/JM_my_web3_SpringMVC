package controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;
import service.UserServiceImpl;

import java.util.List;

@Controller
public class AppController {
    private static final Logger LOG = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
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
