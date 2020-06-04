package site.transcendence.projectmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.transcendence.projectmanager.model.tasks.TaskDto;
import site.transcendence.projectmanager.model.tasks.TaskService;

@Controller
@RequestMapping("/projects/{projectUuid}/tasks")
public class TaskController {

    // TODO: Injection through constructor
    @Autowired
    private TaskService taskService;

    // TODO: Documentation
    @GetMapping("/{code}")
    @ResponseBody
    public TaskDto getTask(@PathVariable("code") String taskCode) {
        return taskService.getTask(taskCode);
    }

    // TODO: Documentation
    @GetMapping
    public String getAllTasks(@PathVariable("projectUuid") String projectUuid,
                              Model model) {
        model.addAttribute("tasks", taskService.getAllTasks(projectUuid));
        return "tasks/task-list";
    }

    // TODO: Documentation
    @PostMapping
    public String createTask(@ModelAttribute("task") TaskDto newTask,
                             @PathVariable("projectUuid") String projectUuid) {
        taskService.createTask(newTask, projectUuid);
        return "redirect:/projects/{projectUuid}/tasks";
    }

    // TODO: Documentation
    @PostMapping("/{code}")
    public String updateTask(@PathVariable("code") String taskCode,
                             @ModelAttribute("task") TaskDto updatedTask) {
        taskService.updateTask(taskCode, updatedTask);
        return "redirect:/projects/{projectUuid}/tasks";
    }

    // TODO: Documentation
    @GetMapping("/{code}/delete")
    public String deleteTask(@PathVariable("code") String taskCode) {
        taskService.deleteTask(taskCode);
        return "redirect:/projects/{projectUuid}/tasks";
    }

}
