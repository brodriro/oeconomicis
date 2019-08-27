package me.rzknairb.domain.usecases;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.rzknairb.domain.entities.Balance;
import me.rzknairb.domain.entities.History;
import me.rzknairb.domain.entities.User;
import me.rzknairb.domain.repositories.BalanceLocalRepositoryImp;
import me.rzknairb.domain.repositories.HistoryLocalRepositoryImp;

@Singleton
public class HomeUseCase {

    @Inject
    BalanceLocalRepositoryImp balanceLocalRepositoryImp;

    @Inject
    HistoryLocalRepositoryImp historyLocalRepositoryImp;

    @Inject
    HomeUseCase() {
    }

    public Single<Balance> getBalanceByUser(User user) throws Exception{
        return balanceLocalRepositoryImp.getBalanceByUser(user);
    }

    public Single<List<History>> getHistory(User user) throws Exception{
        return historyLocalRepositoryImp.getHistory(user);
    }

}
