package me.brian.domain.usecases;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.brian.domain.entities.Balance;
import me.brian.domain.entities.History;
import me.brian.domain.entities.User;
import me.brian.domain.repositories.BalanceDatabaseRepository;
import me.brian.domain.repositories.HistoryDatabaseRepository;

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
