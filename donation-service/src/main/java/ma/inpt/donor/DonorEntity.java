package ma.inpt.donor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "donor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String role = "DONOR";
    private String encryptedPassword;
    private String phoneNumber;
    private String image;
    private String score;
    private String location;

}
