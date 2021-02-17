package exchangerateapp;

import com.mashape.unirest.http.exceptions.UnirestException;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class ExchangeRatesWorker {

    public static void main(String[] args) {
        // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        // Create a Worker factory that can be used to create Workers that poll specific Task Queues.
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(Shared.EXCHANGE_RATE_TASK_QUEUE);
        // This Worker hosts both Workflow and Activity implementations.
        // Workflows are stateful, so you need to supply a type to create instances.
        worker.registerWorkflowImplementationTypes(ExchangeRateWorkFlowImpl.class);
        // Activities are stateless and thread safe, so a shared instance is used.
        worker.registerActivitiesImplementations(new ExchangeCurrencyImpl());
        worker.registerActivitiesImplementations(new CurrencyAPICallImpl());
        // Start polling the Task Queue.
        factory.start();
    }
}
