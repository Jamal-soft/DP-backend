package ma.inpt.organisationService.project;

import ma.inpt.organisationService.model.request.ProjectCreateRequestModel;
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
        ModelMapper modelMapper = new ModelMapper();
        ProjectEntity project = modelMapper.map(projectCreateRequestModel,ProjectEntity.class);
        ProjectEntity project1 = null;
        try{
            project1 = projectRepository.save(project);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return project1;

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
}
