package routing.context;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class RoutingContextWrapper {
    private final String PLAIN_HEADER_NAME = "content-type";
    private final String PLAIN_HEADER_VALUE = "text/plain";

    private final RoutingContext routingContext;

    public RoutingContextWrapper(RoutingContext routingContext) {
        this.routingContext = routingContext;
    }

    public void displayAsPlainContent(String text) {
        HttpServerResponse response = routingContext.response();
        response.putHeader(PLAIN_HEADER_NAME, PLAIN_HEADER_VALUE);
        response.end(text);
    }
}
