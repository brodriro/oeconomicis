package me.rzknairb.domain.usecases;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.rzknairb.domain.entities.User;
import me.rzknairb.domain.repositories.UserDatabaseRepository;

@Singleton
public class LoginUseCase {

    @Inject
    UserDatabaseRepository userDatabaseRepository;

    private User currentUser;

    @Inject
    public LoginUseCase() {
    }


    public Single<Boolean> findUser(String username) throws Exception {
        return userDatabaseRepository.findUser(username);
    }

    public Single<User> createUser(final User user) throws Exception {
        return userDatabaseRepository.createUser(user).map(_user ->{
            currentUser = _user;
            return _user;
        });
    }

    public Single<User> loginUser(String username, String password) throws Exception{
        return userDatabaseRepository.loginUser(username, password).map(user -> {
            if (user != null) currentUser = user;
            return user;
        });
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
