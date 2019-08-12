package r.brian.data.local.repositories;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.brian.domain.entities.User;
import me.brian.domain.repositories.UserDatabaseRepository;
import r.brian.data.local.entities.UserDatabase;

public class UserLocalRepository implements UserDatabaseRepository {

    private final RealmConfiguration realmConfiguration;

    @Inject
    public UserLocalRepository(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    @Override
    public User getUser() throws Exception {
        Realm realm = null;
        try {
            realm = Realm.getInstance(realmConfiguration);
            UserDatabase userEntity = realm.where(UserDatabase.class).findFirst();
            return userEntity != null ? userEntity.toUser() : null;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (realm != null)
                realm.close();
        }
    }

    @Override
    public Boolean saveUser(User user) throws Exception {
        try (Realm realmInstance = Realm.getInstance(realmConfiguration)) {
            realmInstance.executeTransaction(realm -> realm.insertOrUpdate(new UserDatabase(user)));
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Boolean deleteAllUser() throws Exception {
        try (Realm realmInstance = Realm.getInstance(realmConfiguration)) {
            realmInstance.executeTransaction(realm -> realm.delete(UserDatabase.class));
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
