package exchangerateapp;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.client.ActivityCompletionClient;
import io.vertx.core.json.Json;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.concurrent.ForkJoinPool;

public class CurrencyAPICallImpl implements  CurrencyAPICall {
    private final ActivityCompletionClient completionClient;

    CurrencyAPICallImpl(ActivityCompletionClient completionClient) {
        this.completionClient = completionClient;
    }

    @Override
    public JSONObject GetCurrencyConversions() {

        ActivityExecutionContext context = Activity.getExecutionContext();
        byte[] taskToken = context.getTaskToken();

        ForkJoinPool.commonPool().execute(() -> GetCurrencyConversionsAsync(taskToken));

        context.doNotCompleteOnReturn();
        return new JSONObject();
    }

    private void GetCurrencyConversionsAsync(byte[] taskToken){

        JsonNode responseObj = null;
        try {
            responseObj = Unirest
                    .get("https://v6.exchangerate-api.com/v6/a5349596abec82d57d9b2c11/latest/EGP")
                    .asJson()
                    .getBody();
        } catch (UnirestException e){
            e.getStackTrace();
        }

        String stringJson = responseObj.toString();
        JSONParser parser = new JSONParser();

        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(stringJson);
        } catch(ParseException e){
            e.getStackTrace();
        }

        JSONObject result = (JSONObject) json.get("conversion_rates");
        // To complete an activity from a different thread or process use ActivityCompletionClient.
        // In real applications the client is initialized by a process that performs the completion.
        completionClient.complete(taskToken, result);
    }
}
