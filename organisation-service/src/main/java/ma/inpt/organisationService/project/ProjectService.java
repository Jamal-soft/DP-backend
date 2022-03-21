package ma.inpt.organisationService.project;

import ma.inpt.organisationService.model.request.ProjectCreateRequestModel;
import ma.inpt.organisationService.model.response.ProjectListResponseToAdmin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }


    public ProjectEntity createProject(ProjectCreateRequestModel projectCreateRequestModel) {
        ProjectEntity project = new ProjectEntity();

        project.setTitle(projectCreateRequestModel.getTitle());
        project.setTarget(projectCreateRequestModel.getTarget());
        project.setCurrentBalance(projectCreateRequestModel.getCurrentBalance());
        project.setOrgId(projectCreateRequestModel.getOrgId());
        project.setDescription(projectCreateRequestModel.getDescription());
        project.setDateLimit(projectCreateRequestModel.getDateLimit());

        return projectRepository.save(project);


    }
    // une fonction qui va chercher le projet correspond a un id
    public ProjectEntity getProjectById(Long id) {
        ProjectEntity project = projectRepository.findById(id).get();
        return project;
    }

    public List<ProjectEntity> getProjectsOfAnOrganisation(Long orgId) {
        List<ProjectEntity> projects = projectRepository.findByOrgId(orgId);
        return projects;
    }

    public List<ProjectListResponseToAdmin> getProjectsWithTheirOrganisationName() {
        List<ProjectListResponseToAdmin> list = projectRepository.getProjectsWithTheirOrganisationName();
        return list;
    }
}
