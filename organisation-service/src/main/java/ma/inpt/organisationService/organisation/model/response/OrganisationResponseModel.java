package ma.inpt.organisationService.organisation.model.response;

import lombok.Data;

@Data
public class OrganisationResponseModel {
    private Long id;
    private String name;
    private String email;
    private String category;
    private String image;
    private String Location;
    private String verificationFile;
    private String description;
    private String phoneNumber;
}
