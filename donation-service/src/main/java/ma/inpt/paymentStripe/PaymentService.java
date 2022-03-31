package ma.inpt.paymentStripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PaymentService {
    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
    public String charge(PaymentRequest chargeRequest) throws StripeException {
        List<Object> paymentMethodTypes =
                new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", PaymentRequest.Currency.USD);
        chargeParams.put("payment_method", chargeRequest.getId());
        chargeParams.put("confirm", true);
        chargeParams.put("payment_method_types",
                paymentMethodTypes);
        PaymentIntent paymentIntent = PaymentIntent.create(chargeParams);

        return paymentIntent.getId();



    }
}
