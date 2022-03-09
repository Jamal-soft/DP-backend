package ma.inpt.organisationService.organisation;

import ma.inpt.organisationService.organisation.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation,Long> {
    List<Organisation> findByVerified(boolean b);
}
