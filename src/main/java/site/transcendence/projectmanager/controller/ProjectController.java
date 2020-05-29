package site.transcendence.projectmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import site.transcendence.projectmanager.model.project.ProjectDto;
import site.transcendence.projectmanager.model.project.ProjectService;

import java.security.Principal;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * Method sends model of new empty Project to the view
     *
     * @param model contains new empty Project
     * @return view of 'create-project'
     */
    @GetMapping("/add")
    public String createProject(Model model){
        model.addAttribute("project", new ProjectDto());
        return "projects/create-project";
    }

    /**
     * Method sends Project filled with data from 'create-project' view to ProjectService.createProject()
     * If failure, returns 'create-project' view
     * If success, redirects to '/projects'
     *
     * TODO: Project Validation
     *
     * @param project object filled with data passed by user
     * @param principal authorized and authenticated user performing this action is also Project's owner;
     *                  only authorized and authenticated user can perform Project creation
     * @param result contains errors if occurred
     * @return view of 'create-project' if creation failures or redirects to '/projects' if creation success
     */
    @PostMapping("/add")
    public String createProject(@ModelAttribute("project")ProjectDto project,
                                Principal principal,
                                BindingResult result) {
        if (result.hasErrors()){
            return "projects/create-project";
        }
        projectService.createProject(project, principal);
        return "redirect:/projects";
    }

    /**
     * Returns view of 'projects/projects' with list of user's Projects
     * If principal is not owner of the Projects, exception occurs
     *
     * @param model list of principal's projects
     * @param principal authorized and authenticated owner of desired projects list
     * @return view of 'projects/projects'
     */
    @GetMapping
    public String getAllOwnProjects(Model model,
                                    Principal principal){
        model.addAttribute("projects", projectService.getAllProjects(principal));
        return "projects/projects";
    }

    /**
     * Returns Project with given ID which principal is owner of
     * If principal is not owner of the Project, exception occurs
     *
     * @param projectId desired project's ID
     * @param principal authorized and authenticated owner of desired project
     * @param model desired Project
     * @return view of 'projects/project-details/
     */
    @GetMapping("/{projectId}")
    public String getProject(@PathVariable(name = "projectId") Long projectId,
                             Principal principal,
                             Model model){
        model.addAttribute("project", projectService.getProject(projectId, principal));
        return "projects/project-details";
    }

    /**
     * Method sends desired project to the view for further modifications
     * If principal is not owner of the Project, exception occurs
     *
     * @param projectId desired project's ID
     * @param principal authorized and authenticated owner of desired project
     * @param model desired Project
     * @return view of 'projects/edit-project/
     */
    @GetMapping("/{projectId}/edit")
    public String updateProject(@PathVariable("projectId") Long projectId,
                                Principal principal,
                                Model model){
        model.addAttribute("project", projectService.getProject(projectId, principal));
        return "projects/edit-project";
    }

    /**
     * Method sends updated Project to ProjectService.updateProject()
     * If principal is not owner of the Project, exception occurs
     *
     * TODO: Project Validation
     *
     * @param projectId desired project's ID
     * @param project updated Project's object
     * @param principal authorized and authenticated owner of desired project
     * @param result contains errors if occurred
     * @return view of 'edit-project' if update failures or redirects to '/projects' if update success
     */
    @PostMapping("/{projectId}/edit")
    public String updateProject(@PathVariable("projectId") Long projectId,
                                @ModelAttribute("project") ProjectDto project,
                                Principal principal,
                                BindingResult result) {
        if (result.hasErrors()){
            //TODO: Changed to the same style as in creation method, needs to be tested
            //return "/{projectId}/edit";
            return "/projects/edit-project";
        }
        projectService.updateProject(projectId, project, principal);
        return "redirect:/projects";
    }

    /**
     * Delegates Project's deletion to ProjectService.deleteProject()
     * If principal is not owner of the Project, exception occurs
     *
     * @param projectId desired project's ID
     * @param principal authorized and authenticated owner of desired project
     * @return redirects to '/projects'
     */
    @GetMapping("/{projectId}/delete")
    public String deleteProject(@PathVariable(name = "projectId") Long projectId,
                                Principal principal){
        projectService.deleteProject(projectId, principal);
        return "redirect:/projects";
    }

}
