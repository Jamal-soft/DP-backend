package ma.inpt.model;

import lombok.Data;

@Data
public class DonorModelResponse {
    private String name;
    private Long phoneNumber;
    private byte[] image;
}
