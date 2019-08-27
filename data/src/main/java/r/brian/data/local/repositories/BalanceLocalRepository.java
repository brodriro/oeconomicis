package r.brian.data.local.repositories;

import javax.inject.Inject;

import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.rzknairb.domain.entities.Balance;
import me.rzknairb.domain.entities.User;
import me.rzknairb.domain.repositories.BalanceLocalRepositoryImp;
import r.brian.data.Utils;
import r.brian.data.local.entities.BalanceDatabase;

public class BalanceLocalRepository implements BalanceLocalRepositoryImp {

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
                    .findFirstAsync();

            return Single.just(realm.copyFromRealm(balanceDatabase))
                    .filter(balanceDatabase1 -> balanceDatabase1.isLoaded())
                    .flatMapSingle(balanceDatabase1 -> Single.just(balanceDatabase1.toBalance()));
        }
    }
}
