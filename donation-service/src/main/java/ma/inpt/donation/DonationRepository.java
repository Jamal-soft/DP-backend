package ma.inpt.donation;

import ma.inpt.donor.DonorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository  extends JpaRepository<DonationEntity,Long> {
    List<DonationEntity> findAllByOrgId(Long id);
}
