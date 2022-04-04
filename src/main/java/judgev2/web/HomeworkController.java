package judgev2.web;

import judgev2.model.binding.HomeworkAddBindingModel;
import judgev2.security.CurrentUser;
import judgev2.service.ExerciseService;
import judgev2.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
    private final ExerciseService exerciseService;
    private final HomeworkService homeworkService;
    private final CurrentUser currentUser;

    @Autowired
    public HomeworkController(ExerciseService exerciseService, HomeworkService homeworkService, CurrentUser currentUser) {
        this.exerciseService = exerciseService;
        this.homeworkService = homeworkService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    private String add(Model model) {
        if (currentUser.isAnonymous()) {
            return "redirect:/users/login";
        }

        if (!model.containsAttribute("homeworkAddBindingModel")) {
            model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
        }
        model.addAttribute("isLate", false);

        model.addAttribute("exNames", exerciseService.findAllExerciseNames());
        return "homework-add";
    }

    @PostMapping("/add")
    private String addConfirm(
            @Valid HomeworkAddBindingModel homeworkAddBindingModel, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);

            redirectAttributes.addFlashAttribute("org.springframework" +
                                    ".validation.BindingResult.homeworkAddBindingModel", homeworkAddBindingModel);

            return "redirect:add";
        }


        boolean isLate = exerciseService.checkIfIsLate(homeworkAddBindingModel.getExercise());

        if (isLate) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);

            redirectAttributes.addFlashAttribute("isLate", true);
        }

        homeworkService.addHomework(homeworkAddBindingModel.getExercise(), homeworkAddBindingModel.getGithubAddress());

        return "redirect:/";

    }


}
