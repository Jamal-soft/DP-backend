package ma.inpt.paymentStripe;

import lombok.Data;

@Data
public class PaymentRequest {
    public enum Currency{
        USD;
    }
    //private String description;
    private int amount;
    private Currency currency;
    private String id;
    private Long projectId;
    private Long orgId;
    private Long donorId;
}
