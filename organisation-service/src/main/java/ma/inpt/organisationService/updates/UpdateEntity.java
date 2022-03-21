package ma.inpt.organisationService.updates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "updates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String image;
    private Date date = new Date();
    private Long projectId;
}
