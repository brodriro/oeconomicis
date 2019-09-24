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
import me.rzknairb.domain.entities.History;
import me.rzknairb.domain.entities.User;
import me.rzknairb.domain.repositories.HistoryLocalRepository;
import r.brian.data.Utils;
import r.brian.data.local.entities.BalanceDatabase;
import r.brian.data.local.entities.HistoryDatabase;

public class HistoryLocalRepositoryImp implements HistoryLocalRepository {

    private final RealmConfiguration realmConfiguration;

    @Inject
    public HistoryLocalRepositoryImp(RealmConfiguration realmConfiguration) {
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

    /****
     * TODO seprar todo en funciones independientes
     * @param history
     * @return
     */
    @Override
    public Single<Boolean> createHistory(History history) {
        try (Realm realmInstance = Realm.getInstance(realmConfiguration)) {
            realmInstance.beginTransaction();

            Number id = realmInstance.where(HistoryDatabase.class).max("id");
            int nextId = Utils.generateId(id);

            HistoryDatabase historyDatabase = new HistoryDatabase(
                    nextId, history.getIdUser(), history.getIdCategory(), history.getAmount(),
                    history.getCategory_name(), history.getDate_time(), history.getDescription()
            );
            realmInstance.insertOrUpdate(historyDatabase);


            //GET TOTAL AMOUNT OF MONTH
            RealmResults<HistoryDatabase> tmpResult = realmInstance.where(HistoryDatabase.class)
                    .equalTo("idUser", history.getIdUser())
                    .greaterThanOrEqualTo("date_time", Utils.getDateMonth())
                    .findAll();

            double totalAmount = tmpResult.sum("amount").doubleValue();
            Log.e("SUM", totalAmount + "");

            //GET NEXT ID FOR BALANCE TABLE
            Number nIdBalance = realmInstance.where(BalanceDatabase.class).max("id");
            int idBalance = Utils.generateId(nIdBalance);


            //GET BALANCE RECORD OF MONTH
            BalanceDatabase balanceDatabase = realmInstance.where(BalanceDatabase.class)
                    .equalTo("idUser", history.getIdUser()).and()
                    .greaterThanOrEqualTo("date", Utils.getDateMonth())
                    .findFirst();

            //SET NEW AMOUNT OF MONTH
            if (balanceDatabase == null) {
                balanceDatabase = new BalanceDatabase(idBalance, history.getIdUser(), totalAmount, Utils.getDateMonth());
            }
            //UPDATE RECORD
            balanceDatabase.setTotal(totalAmount);
            realmInstance.insertOrUpdate(balanceDatabase);

            realmInstance.commitTransaction();
            return Single.just(true);
        }
    }

}
