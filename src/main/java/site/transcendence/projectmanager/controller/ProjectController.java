package site.transcendence.projectmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import site.transcendence.projectmanager.model.projects.ProjectDto;
import site.transcendence.projectmanager.model.projects.ProjectService;

import java.security.Principal;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * Method sends model of new empty Project to the view representing empty Project's fields
     *
     * @param model contains new empty Project
     * @return Project's creation view
     */
    @GetMapping("/create")
    public String createProject(Model model){
        model.addAttribute("project", new ProjectDto());
        return "projects/project-create";
    }

    /**
     * Method sends Project filled with data from creation view to ProjectService.createProject()
     * Data is filled by authorized and authenticated user which is the project's owner
     * If failure, returns to project creation view
     * If success, redirects to list of projects view
     *
     * TODO: Project Validation
     *
     * @param project object filled with data passed by user
     * @param principal authorized and authenticated user performing this action is also Project's owner;
     *                  only authorized and authenticated user can perform Project creation
     * @param result contains errors if occurred within validation process
     * @return view of 'project-create' if creation failures or redirects to '/projects' if creation success
     */
    @PostMapping("/create")
    public String createProject(@ModelAttribute("project")ProjectDto project,
                                Principal principal,
                                BindingResult result) {
        if (result.hasErrors()){
            return "projects/project-create";
        }
        projectService.createProject(project, principal);
        return "redirect:/projects";
    }

    /**
     * Returns view of 'projects/project-list' with list of user's Projects
     * If principal is not owner of the Projects, exception occurs
     *
     * @param model list of principal's projects
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
     * Method sends desired project to the view for further modifications
     * If principal is not owner of the Project, exception occurs
     *
     * @param projectUuid desired project's UUID
     * @param principal authorized and authenticated owner of desired project
     * @param model desired Project
     * @return view of 'projects/project-update/
     */
    @GetMapping("/{projectUuid}/update")
    public String updateProject(@PathVariable("projectUuid") String projectUuid,
                                Principal principal,
                                Model model){
        model.addAttribute("project", projectService.getProject(projectUuid, principal));
        return "/projects/project-update";
    }

    /**
     * Method sends updated Project to ProjectService.updateProject()
     * If principal is not owner of the Project, exception occurs
     *
     * TODO: Project Validation
     *
     * @param projectUuid desired project's UUID
     * @param project updated Project's object
     * @param principal authorized and authenticated owner of desired project
     * @param result contains errors if occurred
     * @return view of 'project-update' if update failures or redirects to '/projects' if update success
     */
    @PostMapping("/{projectUuid}/update")
    public String updateProject(@PathVariable("projectUuid") String projectUuid,
                                @ModelAttribute("project") ProjectDto project,
                                Principal principal,
                                BindingResult result) {
        if (result.hasErrors()){
            return "/projects/project-update";
        }
        projectService.updateProject(projectUuid, project, principal);
        return "redirect:/projects";
    }

    /**
     * Delegates Project's deletion to ProjectService.deleteProject()
     * If principal is not owner of the Project, exception occurs
     *
     * @param projectUuid desired project's UUID
     * @param principal authorized and authenticated owner of desired project
     * @return redirects to '/projects'
     */
    @GetMapping("/{projectUuid}/delete")
    public String deleteProject(@PathVariable(name = "projectUuid") String projectUuid,
                                Principal principal){
        projectService.deleteProject(projectUuid, principal);
        return "redirect:/projects";
    }

}
