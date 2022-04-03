package ma.inpt.organisationService.project;

import ma.inpt.organisationService.model.request.ProjectCreateRequestModel;
import ma.inpt.organisationService.model.response.ProjectListResponseToAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/organisations/projects")
    public List<ProjectEntity> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PostMapping("/organisations/projects")
    public ProjectEntity createProject(@ModelAttribute ProjectCreateRequestModel projectCreateRequestModel ){
        return projectService.createProject(projectCreateRequestModel);
    }

    @GetMapping("/organisations/projects/{id}")
    public ProjectEntity getProjectById(@PathVariable Long id){
        return projectService.getProjectById(id);
    }

    @GetMapping("/organisations/{orgId}/projects")
    public List<ProjectEntity> getProjectsOfAnOrganisation(@PathVariable Long orgId){
        return projectService.getProjectsOfAnOrganisation(orgId);
    }
//return a list of project with organisation they belong + target + currentBalance

    @GetMapping("/organisations/projects/admin")
    public List<ProjectListResponseToAdmin> getProjectsWithTheirOrganisationName(){
        return projectService.getProjectsWithTheirOrganisationName();
    }
    @PutMapping("/organisations/projects/current-balance/update/{projectId}/{amount}")
    public String updateCurrentBalance(@PathVariable("projectId") Long projectId,@PathVariable("amount") Long amount){
        return projectService.updateCurrentBalance(projectId,amount);
    }

}
