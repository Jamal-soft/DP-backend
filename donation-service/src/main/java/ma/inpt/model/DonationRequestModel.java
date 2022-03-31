package ma.inpt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class DonationRequestModel {
    private Long projectId;
    private Long orgId;
    private Long donorId;
    private Long amount;
}
