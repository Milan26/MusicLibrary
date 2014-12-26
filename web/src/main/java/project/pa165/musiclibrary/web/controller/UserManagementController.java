package project.pa165.musiclibrary.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import project.pa165.musiclibrary.dto.UserDto;
import project.pa165.musiclibrary.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Locale;


/**
* @author Milan
*/
@Controller
@RequestMapping("/user")
public class UserManagementController {

    private UserService userService;
    private MessageSource messageSource;

    public UserService getUserService() {
        return userService;
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Inject
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Locale locale) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error",
                    getMessageSource().getMessage(new DefaultMessageSourceResolvable("user.login.error"), locale));
        }
        if (logout != null) {
            model.addObject("message",
                    getMessageSource().getMessage(new DefaultMessageSourceResolvable("user.login.logout"), locale));
        }
        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView loadUserSignUp() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new UserDto());
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String saveUserSignUp(@Valid @ModelAttribute("user") UserDto user,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user";
        }
        user.setEnabled(true);
        getUserService().createUser(user);
        return "redirect:/";
    }

}
