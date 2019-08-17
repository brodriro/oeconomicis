package r.brian.data.local.repositories;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.brian.domain.entities.History;
import me.brian.domain.repositories.HistoryDatabaseRepository;
import r.brian.data.local.entities.BalanceDatabase;
import r.brian.data.local.entities.HistoryDatabase;

public class HistoryLocalRepository implements HistoryDatabaseRepository {

    private final RealmConfiguration realmConfiguration;

    @Inject
    public HistoryLocalRepository(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    @Override
    public Single<List<History>> getHistory(int idUser, Date date) {
        return null;
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
            double totalAmount = realmInstance.where(HistoryDatabase.class)
                    .equalTo("id", nextId)
                    .greaterThanOrEqualTo("date_time", getDateMonth())
                    .findAll().sum("amount").doubleValue();

            //GET NEXT ID FOR BALANCE TABLE
            Number nIdBalance = realmInstance.where(BalanceDatabase.class).max("id");
            int idBalance = (nIdBalance == null) ? 0 : nIdBalance.intValue() + 1;

            //GET BALANCE RECORD OF MONTH
            BalanceDatabase balanceDatabase = realmInstance.where(BalanceDatabase.class)
                    .equalTo("date", getDateMonth())
                    .equalTo("idUser", history.getIdUser())
                    .findFirst();

            //SET NEW AMOUNT OF MONTH
            if (balanceDatabase == null) {
                balanceDatabase = new BalanceDatabase(idBalance, history.getIdUser(), totalAmount, getDateMonth());
            }
            balanceDatabase.setTotal(totalAmount);

            //UPDATE RECORD
            BalanceDatabase finalBalanceDatabase = balanceDatabase;
            realmInstance.executeTransaction(realm -> realm.insertOrUpdate(finalBalanceDatabase));

            String demo = realmInstance.where(BalanceDatabase.class).findAll().asJSON();
            Log.e("ALL BALANCE", demo);
            return Single.just(true);
        }
    }

    private Date getDateMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.MONTH);
        calendar.get(Calendar.YEAR);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
}
