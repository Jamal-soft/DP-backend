package ma.inpt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class DonationResponseModelToShowInDashboardOfADonor {
    private String organisationName;
    private String projectName;
    private Long amount;
    private Date date;
}
