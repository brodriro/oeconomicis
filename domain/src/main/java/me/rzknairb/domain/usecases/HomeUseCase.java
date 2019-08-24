package me.rzknairb.domain.usecases;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.rzknairb.domain.entities.Balance;
import me.rzknairb.domain.entities.History;
import me.rzknairb.domain.entities.User;
import me.rzknairb.domain.repositories.BalanceDatabaseRepository;
import me.rzknairb.domain.repositories.HistoryDatabaseRepository;

@Singleton
public class HomeUseCase {

    @Inject
    BalanceDatabaseRepository balanceDatabaseRepository;

    @Inject
    HistoryDatabaseRepository historyDatabaseRepository;

    @Inject
    HomeUseCase() {
    }

    public Single<Balance> getBalanceByUser(User user) throws Exception{
        return balanceDatabaseRepository.getBalanceByUser(user);
    }

    public Single<List<History>> getHistory(User user) throws Exception{
        return historyDatabaseRepository.getHistory(user);
    }

}
