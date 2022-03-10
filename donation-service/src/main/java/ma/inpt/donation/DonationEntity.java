package ma.inpt.donation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "donation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private Long orgId;
    private Long donorId;
    private Long amount;
    private Date date;
}
