package exchangerateapp;

import com.mashape.unirest.http.exceptions.UnirestException;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.json.simple.parser.ParseException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class InitiateExchangeRates {

    public static void main(String[] args) throws ParseException, UnirestException, ExecutionException, InterruptedException {
        callWorkflow();
    }

    public static void callWorkflow() throws ExecutionException, InterruptedException {
        // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(Shared.EXCHANGE_RATE_TASK_QUEUE)
                .build();
        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        ExchangeRateWorkFlow workflow = client.newWorkflowStub(ExchangeRateWorkFlow.class, options);

        CompletableFuture<String> RatesToAndFromEGP = WorkflowClient.execute(workflow::getExchangeRates);

        System.out.println(RatesToAndFromEGP.get());
  //        System.exit(0);
    }
}
