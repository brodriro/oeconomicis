package me.brian.domain.repositories;

import io.reactivex.Single;
import me.brian.domain.entities.User;

public interface UserDatabaseRepository {

    Single<Boolean> findUser(String username) throws Exception;

    Single<User> loginUser(String username, String password) throws Exception;

    Single<User> getUser(User user) throws Exception;

    Single<User> createUser(User user) throws Exception;

    boolean deleteAllUser() throws Exception;

}
