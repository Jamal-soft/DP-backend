package ma.inpt.organisationService.model.request;


import lombok.Data;


import java.util.Date;
@Data

public class ProjectCreateRequestModel {

    private String title;
    private String target;
    private Long currentBalance;
    private Long orgId;
    private String description;
    //private byte[] image;
    private Date dateLimit;
}
