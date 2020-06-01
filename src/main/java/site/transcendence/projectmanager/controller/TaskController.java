package site.transcendence.projectmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import site.transcendence.projectmanager.model.tasks.TaskDto;
import site.transcendence.projectmanager.model.tasks.TaskService;

@Controller
@RequestMapping("/projects/{projectUuid}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/create")
    public String createTask(Model model) {
        model.addAttribute("task", new TaskDto());
        return "tasks/task-create";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute("task") TaskDto task,
                             @PathVariable("projectUuid") String projectUuid,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "tasks/task-create";
        }
        taskService.createTask(task, projectUuid);
        return "redirect:/projects/{projectUuid}/tasks";
    }

    @GetMapping
    public String getAllTasks(@PathVariable("projectUuid") String projectUuid,
                              Model model) {
        model.addAttribute("tasks", taskService.getAllTasks(projectUuid));
        return "tasks/task-list";
    }

    @GetMapping("/{taskName}")
    public String getTask(@PathVariable("taskName") String taskName,
                          Model model){
        model.addAttribute("task", taskService.getTask(taskName));
        return "tasks/task-details";
    }

    @GetMapping("/{taskName}/update")
    public String updateTask(@PathVariable("taskName") String taskName,
                                Model model){
        model.addAttribute("task", taskService.getTask(taskName));
        return "tasks/task-update";
    }

    @PostMapping("/{taskName}/update")
    public String updateTask(@PathVariable("taskName") String taskName,
                             @ModelAttribute("task") TaskDto task,
                             BindingResult result){
        if (result.hasErrors()) {
            return "/{taskName}/update";
        }
        taskService.updateTask(taskName, task);
        return "redirect:/projects/{projectUuid}/tasks";
    }

    @GetMapping("/{taskName}/delete")
    public String deleteTask(@PathVariable("taskName") String taskName){
        taskService.deleteTask(taskName);
        return "redirect:/projects/{projectUuid}/tasks";
    }





}
