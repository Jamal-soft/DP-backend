package ma.inpt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DonationResponseSumOfDonationsOfADonorPerOrganisation {
    private String name;
    private Long sum;

}
