package ma.inpt.donation;

import ma.inpt.donor.DonorEntity;
import ma.inpt.model.DonationResponseModelToCalculateSumOfDonations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository  extends JpaRepository<DonationEntity,Long> {
    List<DonationEntity> findAllByOrgId(Long id);
    @Query("select new ma.inpt.model.DonationResponseModelToCalculateSumOfDonations(d.name,sum(don.amount)) from DonorEntity d JOIN DonationEntity don ON don.donorId = d.id group by don.donorId")
    List<DonationResponseModelToCalculateSumOfDonations> getSomeOfDonationsPerDonors();

    @Query("select sum(d.amount) from DonationEntity d")
    Long getTotalSomeOfDonations();
}
