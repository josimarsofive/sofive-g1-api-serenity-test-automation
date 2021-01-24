package tasks;

import model.GetCountryRequest;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AskForCountry implements Task {


    private final String resource = "/ws";
    private final GetCountryRequest countryRequest;

    public AskForCountry(GetCountryRequest countryRequest) {
        this.countryRequest
                = countryRequest;
    }

    public static Performable information(GetCountryRequest countryRequest) {
        return instrumented(AskForCountry.class, countryRequest);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(resource)
                        .with(
                                req -> req.headers(headers())
                                        .body(countryRequest)
                        )
        );
    }

    private HashMap<String, String> headers() {
        return new HashMap<String, String>() {{
            put("Content-Type", "text/xml;charset=UTF-8");
            put("SOAPAction", "");
        }};
    }
}
