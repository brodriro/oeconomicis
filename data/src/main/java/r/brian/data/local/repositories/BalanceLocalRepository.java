package r.brian.data.local.repositories;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.brian.domain.entities.Balance;
import me.brian.domain.entities.User;
import me.brian.domain.repositories.BalanceDatabaseRepository;
import r.brian.data.Utils;
import r.brian.data.local.entities.BalanceDatabase;

public class BalanceLocalRepository implements BalanceDatabaseRepository {

    private final RealmConfiguration realmConfiguration;

    @Inject
    public BalanceLocalRepository(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    @Override
    public Single<Balance> getBalanceByUser(User user) throws Exception {
        try (Realm realm = Realm.getInstance(realmConfiguration)) {
            realm.beginTransaction();
            BalanceDatabase balanceDatabase = realm.where(BalanceDatabase.class)
                    .equalTo("idUser", user.getId()).and()
                    .greaterThanOrEqualTo("date", Utils.getDateMonth())
                    .findFirst();

            if (balanceDatabase == null) return Single.just(null);

            realm.commitTransaction();
            return Single.just(balanceDatabase.toBalance());
        }
    }
}
