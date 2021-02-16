package exchangerateapp;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import org.json.simple.JSONObject;

@ActivityInterface
public interface ExchangeCurrency {

    @ActivityMethod
    String convertEGPToForeign(JSONObject currencies);

    @ActivityMethod
    String convertForeignToEGP(JSONObject currencies);
}
