package ma.inpt.donation;

import ma.inpt.donor.DonorEntity;
import ma.inpt.model.DonationResponseModelToCalculateSumOfDonations;
import ma.inpt.model.DonationResponseModelToShowInDashboardOfADonor;
import ma.inpt.model.DonationResponseSumOfDonationsOfADonorPerOrganisation;
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
    @Query("SELECT new ma.inpt.model.DonationResponseModelToShowInDashboardOfADonor(o.name, p.title, d.amount, d.date) FROM ProjectEntity p, Organisation o, DonationEntity d where p.orgId = o.id and d.donorId= :donorId and d.projectId=p.id and d.orgId=o.id")
    List<DonationResponseModelToShowInDashboardOfADonor> getDonationsOfADonorAndShowItInItsDashboard(Long donorId);
    @Query("SELECT new ma.inpt.model.DonationResponseSumOfDonationsOfADonorPerOrganisation(o.name, sum(d.amount))  FROM DonationEntity d JOIN Organisation o ON d.orgId = o.id and  d.donorId=:donorId group by o.id")
    List<DonationResponseSumOfDonationsOfADonorPerOrganisation> getSumOfDonationsOfADonorPerOrganisation(Long donorId);
}










