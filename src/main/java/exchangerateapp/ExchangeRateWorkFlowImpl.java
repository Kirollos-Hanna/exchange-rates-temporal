package exchangerateapp;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import org.json.simple.JSONObject;

import java.time.Duration;

public class ExchangeRateWorkFlowImpl implements ExchangeRateWorkFlow{

    ActivityOptions options = ActivityOptions.newBuilder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(2))
            .build();

    // ActivityStubs enable calls to Activities as if they are local methods, but actually perform an RPC.
    private final ExchangeCurrency exchangeCurrency = Workflow.newActivityStub(ExchangeCurrency.class, options);

    @Override
    public String getExchangeRates(JSONObject currencies) {
        // This is the entry point to the Workflow.
        // If there were other Activity methods they would be orchestrated here or from within other Activities.
        String foreignToEGP = exchangeCurrency.convertForeignToEGP(currencies);
        String EGPToForeign = exchangeCurrency.convertEGPToForeign(currencies);

        return foreignToEGP + "\n" + EGPToForeign;
    }
}
