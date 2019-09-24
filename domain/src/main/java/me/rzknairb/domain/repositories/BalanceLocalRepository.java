package me.rzknairb.domain.repositories;

import io.reactivex.Single;
import me.rzknairb.domain.entities.Balance;
import me.rzknairb.domain.entities.User;

public interface BalanceLocalRepository {

    Single<Balance> getBalanceByUser(User user) throws Exception;
}
