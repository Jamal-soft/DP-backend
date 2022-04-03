package ma.inpt.organisationService.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OrganisationUpdateRequestModel {
    private String name;
    private String password;
    private String currentPassword;
    private Long rib;
    private String category;
    private MultipartFile image;
    private String Location;
    private String description;
    private String phoneNumber;
}
