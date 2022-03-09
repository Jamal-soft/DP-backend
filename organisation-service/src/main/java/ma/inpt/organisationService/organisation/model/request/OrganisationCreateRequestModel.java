package ma.inpt.organisationService.organisation.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationCreateRequestModel {

    private String name;
    private String email;
    private String password;
    private Long rib;
    private String category;
    private String verified;
    private byte[] verificationFile;
    private byte[] image;
    private String Location;
    private String description;
}
