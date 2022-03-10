package ma.inpt.model;

import lombok.Data;

@Data
public class DonationRequestModel {
    private Long projectId;
    private Long orgId;
    private Long donorId;
    private Long amount;
}
