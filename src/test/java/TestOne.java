import model.GetCountryRequest;
import model.GetCountryResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import tasks.AskForCountry;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SerenityRunner.class)
public class TestOne {

    private static final String URL_BASE = "https://www.crcind.com:443";
    //https://www.crcind.com/csp/samples/SOAP.Demo.CLS?WSDL=1

    @Test
    public void addTwoNumbers() {

      String resource = "/csp/samples/SOAP.Demo.cls";

        HashMap<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "text/xml;charset=UTF-8");
        headers.put("SOAPAction", "http://tempuri.org/SOAP.Demo.AddInteger");

        Actor henry = Actor.named("Henry");

        henry.can(CallAnApi.at(URL_BASE));

        String bodyRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:AddInteger>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:Arg1>1</tem:Arg1>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:Arg2>2</tem:Arg2>\n" +
                "      </tem:AddInteger>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";


        henry.attemptsTo(
                Post.to(resource)
                .with(req -> req.headers(headers).body(bodyRequest))
        );

        System.out.println(LastResponse.received().answeredBy(henry)
                .asString());
    }
}
