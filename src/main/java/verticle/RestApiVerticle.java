package verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

public class RestApiVerticle extends AbstractVerticle {
    private final int port;

    public RestApiVerticle(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        HttpServer server = getVertx().createHttpServer();
        RestApiRouteConfigurator routeConfigurator = new RestApiRouteConfigurator(getVertx());
        server.requestHandler(routeConfigurator.getRouter()::accept).listen(port);
    }
}