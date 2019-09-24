package me.rzknairb.domain.usecases;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.rzknairb.domain.entities.History;
import me.rzknairb.domain.repositories.HistoryLocalRepository;

@Singleton
public class TransactionUseCase {

    @Inject
    HistoryLocalRepository historyLocalRepository;

    @Inject
    public TransactionUseCase() {
    }

    public Single<Boolean> createHistory(History history) throws Exception {
        return historyLocalRepository.createHistory(history);
    }
}
