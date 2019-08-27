package me.rzknairb.domain.repositories;

import java.util.List;

import io.reactivex.Single;
import me.rzknairb.domain.entities.History;
import me.rzknairb.domain.entities.User;

public interface HistoryLocalRepositoryImp {

    Single<List<History>> getHistory(User user);

    Single<Boolean> createHistory(History history);
}
