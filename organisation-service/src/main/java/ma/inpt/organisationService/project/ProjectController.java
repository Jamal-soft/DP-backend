package ma.inpt.organisationService.project;

import ma.inpt.organisationService.model.request.ProjectCreateRequestModel;
import ma.inpt.organisationService.model.response.ProjectListResponseToAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organisations")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    public List<ProjectEntity> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PostMapping("/projects")
    public ProjectEntity createProject(@RequestBody ProjectCreateRequestModel projectCreateRequestModel ){
        return projectService.createProject(projectCreateRequestModel);
    }

    @GetMapping("/projects/{id}")
    public ProjectEntity getProjectById(@PathVariable Long id){
        return projectService.getProjectById(id);
    }

    @GetMapping("/{orgId}/projects")
    public List<ProjectEntity> getProjectsOfAnOrganisation(@PathVariable Long orgId){
        return projectService.getProjectsOfAnOrganisation(orgId);
    }
//return a list of project with organisation they belong + target + currentBalance

    @GetMapping("/projects/admin")
    public List<ProjectListResponseToAdmin> getProjectsWithTheirOrganisationName(){
        return projectService.getProjectsWithTheirOrganisationName();
    }


}
