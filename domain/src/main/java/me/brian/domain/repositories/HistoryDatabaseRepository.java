package me.brian.domain.repositories;

import java.util.List;

import io.reactivex.Single;
import me.brian.domain.entities.History;
import me.brian.domain.entities.User;

public interface HistoryDatabaseRepository {

    Single<List<History>> getHistory(User user);

    Single<Boolean> createHistory(History history);
}
