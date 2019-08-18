package me.brian.domain.repositories;

import io.reactivex.Single;
import me.brian.domain.entities.Balance;
import me.brian.domain.entities.User;

public interface BalanceDatabaseRepository {

    Single<Balance> getBalanceByUser(User user) throws Exception;
}
