package ma.inpt.organisationService.model.request;

import lombok.Data;

@Data
public class OrganisationUpdateRequestModel {
    private String name;
    private String password;
    private Long rib;
    private String category;
    private String image;
    private String Location;
    private String description;
    private String phoneNumber;
}
