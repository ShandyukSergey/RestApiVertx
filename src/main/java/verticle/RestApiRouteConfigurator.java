package verticle;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import repository.InMemoryItemsRepository;
import verticle.handlers.CreateItemHandler;
import verticle.handlers.RootPathHandler;

public class RestApiRouteConfigurator {
    private final String ROOT_PATH = "/";
    private final String CREATE_ITEM_PATH = ROOT_PATH + "create";

    private final Router router;

    RestApiRouteConfigurator(Vertx vertx) {
        this.router = Router.router(vertx);
        bindHandlers();
    }

    private void bindHandlers() {
        router.route(ROOT_PATH).handler(new RootPathHandler());
        router.route(HttpMethod.POST, CREATE_ITEM_PATH)
                .handler(new CreateItemHandler(new InMemoryItemsRepository()));
    }

    public Router getRouter() {
        return router;
    }
}
