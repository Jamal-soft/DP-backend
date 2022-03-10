package ma.inpt.donation;

import ma.inpt.model.DonationRequestModel;
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
    public DonationEntity createDonation(@RequestParam DonationRequestModel donationRequestModel){
        return donationService.createDonation(donationRequestModel);

    }
}
