package r.brian.data.local.repositories;

import javax.inject.Inject;

import io.reactivex.Single;
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
    public Single<Boolean> findUser(String username) throws Exception {
        try (Realm realm = Realm.getInstance(realmConfiguration)) {
            UserDatabase result = realm.where(UserDatabase.class).equalTo("username", username).findFirst();
            realm.close();
            return Single.just(result != null);
        }
    }

    @Override
    public User loginUser(String username, String password) throws Exception {
        return null;
    }

    @Override
    public Single<User> getUser(User user) throws Exception {
        try (Realm realm = Realm.getInstance(realmConfiguration)) {
            UserDatabase userEntity = realm.where(UserDatabase.class).equalTo("username", user.getUsername()).findFirst();
            if (userEntity == null) return null;
            return Single.just(userEntity.toUser());
        }
    }

    @Override
    public Single<User> createUser(User user) throws Exception {
        try (Realm realmInstance = Realm.getInstance(realmConfiguration)) {
            Number id = realmInstance.where(UserDatabase.class).max("id");
            int nextId = (id != null) ? id.intValue() + 1 : 0;
            user.setId(nextId);
            realmInstance.executeTransaction(realm -> realm.insertOrUpdate(new UserDatabase(user)));
            return Single.just(user);
        }
    }

    @Override
    public boolean deleteAllUser() throws Exception {
        try (Realm realmInstance = Realm.getInstance(realmConfiguration)) {
            realmInstance.executeTransaction(realm -> realm.delete(UserDatabase.class));
            return true;
        }
    }
}
