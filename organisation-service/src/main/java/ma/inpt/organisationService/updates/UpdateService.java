package ma.inpt.organisationService.updates;

import ma.inpt.organisationService.model.request.UpdateCreateRequestModel;
import ma.inpt.organisationService.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UpdateService {
    @Autowired
    UpdateRepository updateRepository;
    private Utils utils;

    public UpdateService(Utils utils) {
        this.utils = utils;
    }

    public List<UpdateEntity> getUpdatesOfProject(Long projectId) {
        return updateRepository.findAllByProjectId(projectId);
    }


    public UpdateEntity createUpdateOfProject(UpdateCreateRequestModel updateCreateRequestModel) {
        UpdateEntity updateEntity = new UpdateEntity();
        updateEntity.setProjectId(updateCreateRequestModel.getProjectId());
        updateEntity.setDescription(updateCreateRequestModel.getDescription());

        MultipartFile image = updateCreateRequestModel.getImage();
        if (image!=null){
            String path = utils.uploadFile(image);
            if (path!=null){
                updateEntity.setImage(path);
            }

        }
        return updateRepository.save(updateEntity);


    }

}
