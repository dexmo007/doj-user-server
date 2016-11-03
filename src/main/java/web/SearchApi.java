package web;

import data.User;
import data.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;

/**
 * request-api search for web app
 */
@ManagedBean
@RequestScoped
public class SearchApi {

    @Inject
    private UserService userService;

    private List<User> searchResults = null;

    /*
    user that holds the values for the search
     */
    private User user = new User();

    public void search() {
        searchResults = userService.search(user);
    }

    public User getUser() {
        return user;
    }

    public List<User> getSearchResults() {
        return searchResults;
    }

    public void deleteUser(String id) {
        System.out.println("SearchApi-delete: " + id);
        userService.remove(id);
    }
}
