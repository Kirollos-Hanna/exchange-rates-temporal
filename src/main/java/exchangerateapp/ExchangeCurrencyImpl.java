package exchangerateapp;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ExchangeCurrencyImpl implements ExchangeCurrency{

    private final String[] currencySymbols = new String[]{"USD", "GBP", "EUR"};

    @Override
    public String showConversions(JSONObject currencies){

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

        return EGPToForeign.toString() + "\n" + ForeignToEGP.toString();
    }
}
