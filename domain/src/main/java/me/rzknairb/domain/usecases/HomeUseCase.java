package me.rzknairb.domain.usecases;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.rzknairb.domain.entities.Balance;
import me.rzknairb.domain.entities.History;
import me.rzknairb.domain.entities.User;
import me.rzknairb.domain.repositories.BalanceLocalRepository;
import me.rzknairb.domain.repositories.HistoryLocalRepository;

@Singleton
public class HomeUseCase {

    @Inject
    BalanceLocalRepository balanceLocalRepository;

    @Inject
    HistoryLocalRepository historyLocalRepository;

    @Inject
    HomeUseCase() {
    }

    public Single<Balance> getBalanceByUser(User user) throws Exception{
        return balanceLocalRepository.getBalanceByUser(user);
    }

    public Single<List<History>> getHistory(User user) throws Exception{
        return historyLocalRepository.getHistory(user);
    }

}
