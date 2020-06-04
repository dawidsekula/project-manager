package site.transcendence.projectmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.transcendence.projectmanager.model.projects.ProjectDto;
import site.transcendence.projectmanager.model.projects.ProjectService;

import java.security.Principal;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    // TODO: Injection through constructor
    @Autowired
    private ProjectService projectService;

    /**
     * TODO: Project Validation with custom validators
     *
     * @param project object with data filled by user
     * @param principal authorized and authenticated user performing this action is also Project's owner;
     *                  only authorized and authenticated user can perform Project creation
     * @return view of 'project-create' if creation failures or redirects to '/projects' if creation success
     */
    @PostMapping
    public String createProject(@ModelAttribute("project")ProjectDto project,
                                Principal principal) {
        projectService.createProject(project, principal);
        return "redirect:/projects";
    }

    /**
     * @param model contains "projects" attribute with list of principal's Projects
     * @param principal authorized and authenticated owner of desired projects list
     * @return view of 'projects/project-list'
     */
    @GetMapping
    public String getAllOwnProjects(Model model,
                                    Principal principal){
        model.addAttribute("projects", projectService.getAllProjects(principal));
        return "projects/project-list";
    }

    /**
     * @param projectUuid desired project's UUID
     * @param principal authorized and authenticated owner of the project
     * @return Project's object in JSON
     */
    @GetMapping("/{projectUuid}")
    @ResponseBody
    public ProjectDto getProject(@PathVariable("projectUuid") String projectUuid,
                                Principal principal){
        return projectService.getProject(projectUuid, principal);
    }

    /**
     * TODO: Project Validation with custom validators
     *
     * @param projectUuid desired project's UUID
     * @param project updated Project's object
     * @param principal authorized and authenticated owner of project
     * @return redirects to '/projects'
     */
    @PostMapping("/{projectUuid}")
    public String updateProject(@PathVariable("projectUuid") String projectUuid,
                                @ModelAttribute("project") ProjectDto project,
                                Principal principal) {
        projectService.updateProject(projectUuid, project, principal);
        return "redirect:/projects";
    }

    /**
     * @param projectUuid desired project's UUID
     * @param principal authorized and authenticated owner of project
     * @return redirects to '/projects'
     */
    @GetMapping("/{projectUuid}/delete")
    public String deleteProject(@PathVariable(name = "projectUuid") String projectUuid,
                                Principal principal){
        projectService.deleteProject(projectUuid, principal);
        return "redirect:/projects";
    }

}
