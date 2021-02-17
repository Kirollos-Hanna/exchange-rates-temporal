package exchangerateapp;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InitiateExchangeRates {

    public static void main(String[] args) throws Exception {
        // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(Shared.EXCHANGE_RATE_TASK_QUEUE)
                .build();
        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        ExchangeRateWorkFlow workflow = client.newWorkflowStub(ExchangeRateWorkFlow.class, options);

        // Synchronously execute the Workflow and wait for the response.
        String RatesToAndFromEGP = workflow.getExchangeRates();
        System.out.println(RatesToAndFromEGP);
        System.exit(0);
    }
}
