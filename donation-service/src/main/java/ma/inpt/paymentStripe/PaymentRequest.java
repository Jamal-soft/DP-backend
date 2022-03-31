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
}
