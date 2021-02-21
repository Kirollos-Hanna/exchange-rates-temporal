package vertxserver;

import com.mashape.unirest.http.exceptions.UnirestException;
import exchangerateapp.InitiateExchangeRates;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import org.json.simple.parser.ParseException;

import java.util.concurrent.ExecutionException;

public class VertxServer extends AbstractVerticle {

    public static void main(String[] args) throws Exception {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        //Route matches EVERY request coming to it for now
        router.route().path("/call-workflow").handler(ctx -> {

            // This handler will be called for every request
            HttpServerResponse response = ctx.response();
            response.putHeader("content-type", "text/plain");
            try {
                InitiateExchangeRates.main(new String[]{});
                response.end("Hello World from Vert.x-Web!");
            } catch (ParseException | UnirestException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            // Write to the response and end it
        });

        server.requestHandler(router).listen(8080);
    }
}
