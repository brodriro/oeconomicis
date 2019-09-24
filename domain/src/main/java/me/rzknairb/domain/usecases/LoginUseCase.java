package me.rzknairb.domain.usecases;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.rzknairb.domain.entities.User;
import me.rzknairb.domain.repositories.UserLocalRepository;

@Singleton
public class LoginUseCase {

    @Inject
    UserLocalRepository userLocalRepository;

    private User currentUser;

    @Inject
    public LoginUseCase() {
    }


    public Single<Boolean> findUser(String username) throws Exception {
        return userLocalRepository.findUser(username);
    }

    public Single<User> createUser(final User user) throws Exception {
        return userLocalRepository.createUser(user).map(_user ->{
            currentUser = _user;
            return _user;
        });
    }

    public Single<User> loginUser(String username, String password) throws Exception{
        return userLocalRepository.loginUser(username, password).map(user -> {
            if (user != null) currentUser = user;
            return user;
        });
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
