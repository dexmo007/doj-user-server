package web;

import data.User;
import data.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * api for web pages
 */
@Named
@ApplicationScoped
public class WebApi {

    @Inject
    private UserService userService;

    public List<User> listUsers(){
        return userService.getAll();
    }

    public void deleteUser(String id){
        userService.remove(id);
    }

}
