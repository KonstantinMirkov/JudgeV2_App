package judgev2.web;

import judgev2.model.binding.CommentAddBindingModel;
import judgev2.model.service.CommentServiceModel;
import judgev2.model.view.HomeworkViewModel;
import judgev2.security.CurrentUser;
import judgev2.service.CommentService;
import judgev2.service.HomeworkService;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/comments")
public class CommentController {
    private final HomeworkService homeworkService;
    private final ModelMapper modelMapper;
    private final CommentService commentService;
    private final CurrentUser currentUser;

    @Autowired
    public CommentController(HomeworkService homeworkService, ModelMapper modelMapper, CommentService commentService, CurrentUser currentUser) {
        this.homeworkService = homeworkService;
        this.modelMapper = modelMapper;
        this.commentService = commentService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    private String add(Model model) {

        if (currentUser.isAnonymous()) {
            return "redirect:/users/login";
        }

        if (!model.containsAttribute("commentAddBindingModel")) {
            model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
        }

        model.addAttribute("homework", modelMapper.map(homeworkService.findByScoring(), HomeworkViewModel.class));

        return "homework-check";
    }

    @PostMapping("/add")
    private String addConfirm(@Valid CommentAddBindingModel commentAddBindingModel
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentAddBindingModel", commentAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);
            return "redirect:add";
        }
        
        CommentServiceModel commentServiceModel = modelMapper
                .map(commentAddBindingModel, CommentServiceModel.class);

        commentService.add(commentServiceModel, commentAddBindingModel.getHomeworkId());

        return "redirect:/";
    }
}
