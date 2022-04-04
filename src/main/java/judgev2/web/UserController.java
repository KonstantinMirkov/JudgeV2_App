package judgev2.web;

import judgev2.model.binding.UserLoginBindingModel;
import judgev2.model.binding.UserRegisterBindingModel;
import judgev2.model.service.UserServiceModel;
import judgev2.security.CurrentUser;
import judgev2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @GetMapping("/register")
    private String register(Model model) {
        if (!currentUser.isAnonymous()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    private String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);

            redirectAttributes.addFlashAttribute("org.springframework" +
                    ".validation.BindingResult" +
                    ".userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("passwordDiff", true);
            redirectAttributes.addFlashAttribute("passwordDiffMessage", "Mismatched passwords");
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);

            return "redirect:register";
        }

        userService.register(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    private String login(Model model) {
        if (!currentUser.isAnonymous()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }

    @PostMapping("/login")
    private String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);

            redirectAttributes.addFlashAttribute("org.springframework" +
                    ".validation.BindingResult" +
                    ".userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        UserServiceModel userServiceModel = userService.findByUsernameAndPassword(modelMapper.map(userLoginBindingModel, UserServiceModel.class));

        if (userServiceModel == null) {
            redirectAttributes.addFlashAttribute("notFound", true);
            redirectAttributes.addFlashAttribute("notFoundMsg", "Incorrect username or password");
            return "redirect:login";
        }

        userService.login(userServiceModel);
        return "redirect:/";
    }

    @GetMapping("/logout")
    private String logout() {
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    private String profile(@PathVariable String id, Model model) {

        model.addAttribute("user", userService.findProfileById(id));

        return "profile";
    }
}
