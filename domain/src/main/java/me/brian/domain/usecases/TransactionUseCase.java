package me.brian.domain.usecases;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.brian.domain.entities.History;
import me.brian.domain.repositories.HistoryDatabaseRepository;

@Singleton
public class TransactionUseCase {

    @Inject
    HistoryDatabaseRepository historyDatabaseRepository;

    @Inject
    public TransactionUseCase() {
    }

    public Single<Boolean> createHistory(History history) throws Exception {
        return historyDatabaseRepository.createHistory(history);
    }
}
