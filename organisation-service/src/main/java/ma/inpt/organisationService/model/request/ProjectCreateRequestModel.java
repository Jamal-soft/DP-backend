package ma.inpt.organisationService.model.request;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;
@Data

public class ProjectCreateRequestModel {

    private String title;
    private String target;
    private Long currentBalance;
    private Long orgId;
    private String description;
    private MultipartFile image;
    private Date dateLimit;
}
