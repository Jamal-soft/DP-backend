package ma.inpt.organisationService.updates;

import ma.inpt.organisationService.model.request.UpdateCreateRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateService {
    @Autowired
    UpdateRepository updateRepository;

    public List<UpdateEntity> getUpdatesOfProject(Long projectId) {
        return updateRepository.findAllByProjectId(projectId);
    }


    public UpdateEntity createUpdateOfProject(UpdateCreateRequestModel updateCreateRequestModel) {
        UpdateEntity updateEntity = new UpdateEntity();
        updateEntity.setProjectId(updateCreateRequestModel.getProjectId());
        updateEntity.setDescription(updateCreateRequestModel.getDescription());
        updateEntity.setImage(updateCreateRequestModel.getImage());
        return updateRepository.save(updateEntity);


    }

}
