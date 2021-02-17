# Description

Use temporal to get display the exchange rates of the egyptian pound to the dollar, sterling and euro, and vice-versa.

## prerequisites

Refer to the [temporal docs here](https://docs.temporal.io/docs/java-sdk-tutorial-prerequisites) for info on the required prerequisites
## How to Run

You can either use the IntelliJ IDE to run the worker and job or use gradle in the following way:
- cd into the project directory.
- Use <code>./gradlew build</code> to build the project.
- Use <code>./gradlew startWorkerForExchangeRates</code> to start the worker.
- Finally, use <code>./gradlew getExchangeRates</code> to start the workflow.

Don't forget that you can view all the info about the activites that were run through the temporal server which will be on localhost:8088 as of the writing of this doc.