package ma.inpt.organisationService.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long orgId;
    private String target;
    private Long currentBalance;
    private String description;
    private String image;
    private Date dateLimit;
}
