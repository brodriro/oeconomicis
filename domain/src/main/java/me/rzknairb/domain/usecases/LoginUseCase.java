package me.rzknairb.domain.usecases;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.rzknairb.domain.entities.User;
import me.rzknairb.domain.repositories.UserLocalRepositoryImp;

@Singleton
public class LoginUseCase {

    @Inject
    UserLocalRepositoryImp userLocalRepositoryImp;

    private User currentUser;

    @Inject
    public LoginUseCase() {
    }


    public Single<Boolean> findUser(String username) throws Exception {
        return userLocalRepositoryImp.findUser(username);
    }

    public Single<User> createUser(final User user) throws Exception {
        return userLocalRepositoryImp.createUser(user).map(_user ->{
            currentUser = _user;
            return _user;
        });
    }

    public Single<User> loginUser(String username, String password) throws Exception{
        return userLocalRepositoryImp.loginUser(username, password).map(user -> {
            if (user != null) currentUser = user;
            return user;
        });
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
