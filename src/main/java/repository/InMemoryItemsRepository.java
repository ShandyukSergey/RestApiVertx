package repository;


import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class InMemoryItemsRepository implements ItemsRepository {
    private final List<String> items = new ArrayList<>();

    @Override
    public Observable<Boolean> save(String name) {
        items.add(name);
        return Observable.just(true);
    }

    @Override
    public Observable<List<String>> findAll() {
        return Observable.just(items);
    }
}
