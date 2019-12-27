package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class TestController {

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("title", "Login page");
        model.addAttribute("message", "Please log in app");
        return "index";
    }

/*    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login page");
        model.addObject("message", "Please log in app");
        model.setViewName("login");
        return model;
    }*/
}
