package ma.inpt.organisationService.organisation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.inpt.organisationService.project.ProjectEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "organisation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String encryptedPassword;
    private Long rib;
    private String category;
    private boolean verified;
    private byte[] verificationFile;
    private byte[] image;
    private String Location;
    private String description;
/*
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgId")
    private List<ProjectEntity> projects;
*/



}
