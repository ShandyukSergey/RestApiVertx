package repository;


import io.reactivex.Observable;

import java.util.List;

public interface ItemsRepository {
    Observable<Boolean> save(String name);

    Observable<List<String>> findAll();
}
