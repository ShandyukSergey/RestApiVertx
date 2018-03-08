package verticle.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import routing.context.RoutingContextWrapper;

public class RootPathHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext event) {
        new RoutingContextWrapper(event).displayAsPlainContent("Api verticle is running...");
    }
}
