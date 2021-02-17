package exchangerateapp;

import com.mashape.unirest.http.exceptions.UnirestException;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

@WorkflowInterface
public interface ExchangeRateWorkFlow {

    @WorkflowMethod
    String getExchangeRates() throws ParseException, UnirestException;
}
