package ma.inpt.organisationService.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateCreateRequestModel {
    private Long projectId;
    private String description;
    private MultipartFile image;
}
