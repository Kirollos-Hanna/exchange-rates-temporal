package exchangerateapp;

import com.mashape.unirest.http.exceptions.UnirestException;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

@ActivityInterface
public interface CurrencyAPICall {
    @ActivityMethod
    JSONObject GetCurrencyConversions();
}
