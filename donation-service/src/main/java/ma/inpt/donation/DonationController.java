package ma.inpt.donation;

import ma.inpt.model.DonationRequestModel;
import ma.inpt.model.DonationResponseModelToCalculateSumOfDonations;
import ma.inpt.model.DonationResponseModelToShowInDashboardOfADonor;
import ma.inpt.model.DonationResponseSumOfDonationsOfADonorPerOrganisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DonationController {
    @Autowired
    DonationService donationService;

    @GetMapping("/donations/{orgId}")
    public List<DonationEntity> getDonationByOrgId(@PathVariable Long orgId){
        return donationService.getDonationByOrgId(orgId);

    }

    @PostMapping("/donations")
    public DonationEntity createDonation(@RequestBody DonationRequestModel donationRequestModel){
        return donationService.createDonation(donationRequestModel);

    }
    @GetMapping("/donations/sum")
    public List<DonationResponseModelToCalculateSumOfDonations> getSomeOfDonationsPerDonors(){
        return donationService.getSomeOfDonationsPerDonors();

    }
    @GetMapping("/donations/total-sum")
    public Long getTotalSomeOfDonations(){
        return donationService.getTotalSomeOfDonations();

    }
    @GetMapping("/donations/donor-dashboard/{donorId}")
    public List<DonationResponseModelToShowInDashboardOfADonor> getDonationsOfADonorAndShowItInItsDashboard(@PathVariable(name = "donorId") Long donorId){
        return donationService.getDonationsOfADonorAndShowItInItsDashboard(donorId);

    }
    @GetMapping("/donations/donor-dashboard/sum-donation/{donorId}")
    public List<DonationResponseSumOfDonationsOfADonorPerOrganisation> getSumOfDonationsOfADonorPerOrganisation(@PathVariable(name = "donorId") Long donorId){
        return donationService.getSumOfDonationsOfADonorPerOrganisation(donorId);

    }
}
