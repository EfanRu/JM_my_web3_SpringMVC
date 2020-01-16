package controller;

import model.Role;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AppController {
    private static final Logger LOG = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        LOG.info("Inside login or welcome page!");
        return "login";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String add(HttpServletRequest req) {
        LOG.info("Inside add!");
        Role role = Role.parseRole(req.getParameter("role"));
        userService.addUser(new User(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("login"),
                req.getParameter("password"),
                Long.parseLong(req.getParameter("phoneNumber")),
                Role.parseRole(req.getParameter("role"))));
        return "redirect:/admin/all";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute(user);
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
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUsers";
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public String editUser(HttpServletRequest req) {
        userService.updateUser(
                req.getParameter("id"),
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("phoneNumber"),
                req.getParameter("role"),
                req.getParameter("login"),
                req.getParameter("password"));
        return "redirect:/admin/all";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String delUser(@ModelAttribute("delId") String id) {
        userService.deleteUser(id);
        return "redirect:/admin/all";
    }
}
