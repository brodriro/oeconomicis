package r.brian.data.local.repositories;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;
import me.brian.domain.entities.History;
import me.brian.domain.entities.User;
import me.brian.domain.repositories.HistoryDatabaseRepository;
import r.brian.data.Utils;
import r.brian.data.local.entities.BalanceDatabase;
import r.brian.data.local.entities.HistoryDatabase;

public class HistoryLocalRepository implements HistoryDatabaseRepository {

    private final RealmConfiguration realmConfiguration;

    @Inject
    public HistoryLocalRepository(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    @Override
    public Single<List<History>> getHistory(User user) {
        try (Realm realmInstance = Realm.getInstance(realmConfiguration)) {
            List<HistoryDatabase> results = realmInstance.where(HistoryDatabase.class)
                    .equalTo("idUser", user.getId()).and()
                    .greaterThanOrEqualTo("date_time", Utils.getDateMonth())
                    .sort("date_time", Sort.DESCENDING)
                    .findAll();
            if (results == null) return Single.just(new ArrayList<>());

            return Single.just(HistoryDatabase.toHistores(results));
        }
    }

    @Override
    public Single<Boolean> createHistory(History history) {
        try (Realm realmInstance = Realm.getInstance(realmConfiguration)) {
            Number id = realmInstance.where(HistoryDatabase.class).max("id");
            int nextId = (id != null) ? id.intValue() + 1 : 0;
            HistoryDatabase historyDatabase = new HistoryDatabase(
                    nextId, history.getIdUser(), history.getIdCategory(), history.getAmount(),
                    history.getCategory_name(), history.getDate_time(), history.getDescription()
            );

            realmInstance.executeTransaction(realm -> realmInstance.insertOrUpdate(historyDatabase));

            //GET TOTAL AMOUNT OF MONTH
            RealmResults<HistoryDatabase> tmpResult = realmInstance.where(HistoryDatabase.class)
                    .equalTo("idUser", history.getIdUser() )
                    .greaterThanOrEqualTo("date_time", Utils.getDateMonth())
                    .findAll();
            Log.e("RESULTS as json", tmpResult.asJSON());
            double totalAmount = tmpResult.sum("amount").doubleValue();
            Log.e("SUM", totalAmount+ "") ;

            //GET NEXT ID FOR BALANCE TABLE
            Number nIdBalance = realmInstance.where(BalanceDatabase.class).max("id");
            int idBalance = (nIdBalance == null) ? 0 : nIdBalance.intValue() + 1;



            //GET BALANCE RECORD OF MONTH
            BalanceDatabase balanceDatabase = realmInstance.where(BalanceDatabase.class)
                    .equalTo("idUser", history.getIdUser()).and()
                    .greaterThanOrEqualTo("date", Utils.getDateMonth())
                    .findFirstAsync();

            //SET NEW AMOUNT OF MONTH
            if (balanceDatabase == null) {
                balanceDatabase = new BalanceDatabase(idBalance, history.getIdUser(), totalAmount, Utils.getDateMonth());
            }


            //UPDATE RECORD
            BalanceDatabase finalBalanceDatabase = balanceDatabase;
            realmInstance.executeTransaction(realm -> {
                finalBalanceDatabase.setTotal(totalAmount);
                realm.insertOrUpdate(finalBalanceDatabase);
            });

            return Single.just(true);
        }
    }

}
