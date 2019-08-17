package me.brian.domain.repositories;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import me.brian.domain.entities.History;

public interface HistoryDatabaseRepository {

    Single<List<History>> getHistory(int idUser, Date date);

    Single<Boolean> createHistory(History history);
}
