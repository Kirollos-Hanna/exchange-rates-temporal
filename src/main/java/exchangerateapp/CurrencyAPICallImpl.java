package exchangerateapp;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CurrencyAPICallImpl implements  CurrencyAPICall {

    @Override
    public JSONObject GetCurrencyConversions() throws UnirestException, ParseException {

        JsonNode responseObj = Unirest
                .get("https://v6.exchangerate-api.com/v6/a5349596abec82d57d9b2c11/latest/EGP")
                .asJson()
                .getBody();

        String stringJson = responseObj.toString();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(stringJson);


        return (JSONObject) json.get("conversion_rates");
    }

}
