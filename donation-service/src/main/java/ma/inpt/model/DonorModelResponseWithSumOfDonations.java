package ma.inpt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonorModelResponseWithSumOfDonations {
    private String name;
    private Long phoneNumber;
    private String image;
    private String score;
    private String location;
    private Long totalDonations;

}
