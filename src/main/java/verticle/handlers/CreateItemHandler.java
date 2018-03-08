package verticle.handlers;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import repository.ItemsRepository;
import routing.context.RoutingContextWrapper;

import java.util.stream.Collectors;

public class CreateItemHandler implements Handler<RoutingContext> {
    private final String ITEM_NAME_PARAM = "item_name";
    private final String ERROR_MESSAGE = "An error has occur:%s";
    private final String SUCCESS_MESSAGE = "Created: %s\n\nSaved products:\n%s";

    private final ItemsRepository itemsRepository;

    public CreateItemHandler(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public void handle(RoutingContext routingContext) {
        RoutingContextWrapper routingContextWrapper = new RoutingContextWrapper(routingContext);
        String itemName = routingContext.request().getParam(ITEM_NAME_PARAM);

        if (itemName != null && !itemName.isEmpty()) {
            itemsRepository
                    .save(itemName)
                    .flatMap((Function<Boolean, Observable<String>>) result -> getSavedItems())
                    .map(savedItems -> String.format(SUCCESS_MESSAGE, itemName, savedItems))
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            routingContextWrapper::displayAsPlainContent,
                            throwable ->
                                    routingContextWrapper.displayAsPlainContent(
                                            String.format(ERROR_MESSAGE, throwable.toString())));
        } else {
            routingContextWrapper.displayAsPlainContent(String.format(ERROR_MESSAGE, "empty name"));
        }
    }

    private Observable<String> getSavedItems() {
        return itemsRepository
                .findAll()
                .map(items -> items.stream().collect(Collectors.joining("\n")));
    }
}
