package hello;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@PropertySource("classpath:application.properties")
public class SmsController {

    @Value("${twilio.from}")
    private String fromPhoneNumber;

    @Value("${defaults.to}")
    private String defaultTo;

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;


    private String withDefault(String value, String defaultValue) {
        return value != null && !value.trim().isEmpty() ? value : defaultValue;
    }

    @RequestMapping(value = "/config")
    public String onlyForTesting() {
        return "fromPhoneNumber: " + fromPhoneNumber + "\n"
                + "defaultTo: " + defaultTo + "\n"
                + "accountSid: " + accountSid + "\n"
                + "authToken: " + authToken + "\n";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    public Response sendSMSByGET(@RequestParam(required = false) String to, @RequestParam(required = false) String message) {
        Request request = new Request();
        request.setTo(withDefault(to, defaultTo));
        request.setMessage(withDefault(message, "Hello World!!"));
        return sendSMSByPPOST(request);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
    public Response sendSMSByPPOST(@RequestBody Request request) {
        Twilio.init(accountSid, authToken);

        PhoneNumber to = new PhoneNumber(request.getTo());
        PhoneNumber from = new PhoneNumber(fromPhoneNumber);
        String message = request.getMessage();

        Message result = Message.creator(to, from, message).create();

        return new Response(result.getSid());
    }

}
