package ma.inpt.organisationService.project;

import com.cloudinary.Cloudinary;
import ma.inpt.organisationService.model.request.ProjectCreateRequestModel;
import ma.inpt.organisationService.model.response.ProjectListResponseToAdmin;
import ma.inpt.organisationService.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    private Utils utils;

    public ProjectService(Utils utils) {
        this.utils = utils;
    }

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
        MultipartFile image = projectCreateRequestModel.getImage();
        if (image!=null){
            String path = utils.uploadFile(image);
            if (path!=null){
                project.setImage(path);
            }

        }

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

    public String updateCurrentBalance(Long projectId,Long amount) {
        ProjectEntity project = projectRepository.findById(projectId).get();
        if (project!=null){
            project.setCurrentBalance(project.getCurrentBalance() + amount);
            projectRepository.save(project);
            return "current balance updated succesfully";
        }
        return "project not found";
    }
}
