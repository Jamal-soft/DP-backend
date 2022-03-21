package ma.inpt.organisationService.model.request;

import lombok.Data;

@Data
public class UpdateCreateRequestModel {
    private Long projectId;
    private String description;
    private String image;
}
