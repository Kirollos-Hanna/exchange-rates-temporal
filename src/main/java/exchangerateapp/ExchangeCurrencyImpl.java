package exchangerateapp;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.client.ActivityCompletionClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.concurrent.ForkJoinPool;

public class ExchangeCurrencyImpl implements ExchangeCurrency{

    private final String[] currencySymbols = new String[]{"USD", "GBP", "EUR"};

    private final ActivityCompletionClient completionClient;

    ExchangeCurrencyImpl(ActivityCompletionClient completionClient) {
        this.completionClient = completionClient;
    }

    @Override
    public String showConversions(JSONObject currencies){


        ActivityExecutionContext context = Activity.getExecutionContext();
        byte[] taskToken = context.getTaskToken();

        ForkJoinPool.commonPool().execute(() -> showConversionsAsync(taskToken, currencies));

        context.doNotCompleteOnReturn();
        return "Ignored";
    }

    public void showConversionsAsync(byte[] taskToken, JSONObject currencies){

        StringBuilder EGPToForeign = new StringBuilder();
        for (String currency : currencySymbols) {
            EGPToForeign
                    .append("EGP to ")
                    .append(currency)
                    .append(": ")
                    .append(String.format("%.3f", (double) currencies.get(currency)))
                    .append(" ");
        }

        StringBuilder ForeignToEGP = new StringBuilder();
        for (String currency : currencySymbols) {
            ForeignToEGP
                    .append(currency)
                    .append(" to EGP")
                    .append(": ")
                    .append(String.format("%.3f", 1 / (double) currencies.get(currency)))
                    .append(" ");
        }

        String result =  EGPToForeign.toString() + "\n" + ForeignToEGP.toString();

        completionClient.complete(taskToken, result);
    }
}
