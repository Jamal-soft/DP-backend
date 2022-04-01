package ma.inpt.model;

import lombok.Data;

@Data
public class DonorModelResponse {
    private Long id;
    private String name;
    private Long phoneNumber;
    private String image;
    private String score;
    private String location;
}
