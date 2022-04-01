package ma.inpt.organisationService.updates;

import ma.inpt.organisationService.model.request.UpdateCreateRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organ")
public class UpdateController {
    @Autowired
    UpdateService updateService;

    @GetMapping("/updates/{projectId}")
    public List<UpdateEntity> getUpdatesOfProject(@PathVariable(name = "projectId") Long projectId){
        return updateService.getUpdatesOfProject(projectId);
    }

    @PostMapping("/updates")
    public UpdateEntity createUpdateOfProject(@ModelAttribute UpdateCreateRequestModel updateCreateRequestModel ){
        return updateService.createUpdateOfProject(updateCreateRequestModel);
    }




}
