package ma.inpt.organisationService.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectListResponseToAdmin {
    private String title;
    private String target;
    private String name;
    private Long currentBalance;

}
