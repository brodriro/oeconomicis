package me.brian.domain.repositories;

import me.brian.domain.entities.User;

public interface UserDatabaseRepository {
    User getUser() throws Exception;

    Boolean saveUser(User user) throws Exception;

    Boolean deleteAllUser() throws Exception;

}
