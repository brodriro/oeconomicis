package me.rzknairb.domain.usecases;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.rzknairb.domain.entities.History;
import me.rzknairb.domain.repositories.HistoryDatabaseRepository;

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
