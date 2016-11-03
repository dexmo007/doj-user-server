package web;

import data.User;
import data.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * request-api for adding a user
 */
@Named
@RequestScoped
public class AddApi {

    @Inject
    private UserService userService;

    private User user = new User();

    public void performAdding() {
        userService.add(user);
    }

    public User getUser() {
        return user;
    }
}
