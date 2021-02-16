package exchangerateapp;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.json.simple.JSONObject;

@WorkflowInterface
public interface ExchangeRateWorkFlow {

    @WorkflowMethod
    String getExchangeRates(JSONObject currencies);
}
