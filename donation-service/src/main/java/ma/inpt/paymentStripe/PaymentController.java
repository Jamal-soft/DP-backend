package ma.inpt.paymentStripe;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")

public class PaymentController {
    @Autowired
    PaymentService service;

    @PostMapping
    public ResponseEntity<ResponseModelStripe> completePayment(@RequestBody PaymentRequest request) throws StripeException {
        String chargeId= service.charge(request);
        return chargeId!=null? new ResponseEntity<ResponseModelStripe>(new ResponseModelStripe("Payment Successful",true), HttpStatus.OK):
                new ResponseEntity<ResponseModelStripe>(new ResponseModelStripe("Payment Failed",false),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public String handleError(StripeException ex) {
        return ex.getMessage();
    }
}
