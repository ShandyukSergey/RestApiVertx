package repository;

import io.reactivex.Observable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import java.util.Collections;
import java.util.List;

public class MongoItemsRepository implements ItemsRepository {
    private final String COLLECTION_NAME = "itemsCollection";
    private final String ITEM_KEY = "name";

    private final MongoClient mongoClient;

    public MongoItemsRepository(Vertx vertx, JsonObject config) {
        mongoClient = MongoClient.createNonShared(vertx, config);
    }

    @Override
    public Observable<Boolean> save(String name) {
        return Observable.just(true);
//        return mongoClient.fi.getCollection(COLLECTION_NAME)
//                .insertOne(new Document(ITEM_KEY, name), new SingleResultCallback<Void>() {
//                    @Override
//                    public void onResult(Void result, Throwable t) {
//
//                    }
//                })
//                .map(success -> true);
    }

    @Override
    public Observable<List<String>> findAll() {
        mongoClient.find(COLLECTION_NAME, new JsonObject(), AsyncResult::result);
        return Observable.just(Collections.EMPTY_LIST);

        //        return database
//                .getCollection(COLLECTION_NAME)
//                .find()
//                .toObservable()
//                .map(document -> document.getString(ITEM_KEY))
//                .toList();
    }
}
