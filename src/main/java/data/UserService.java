package data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * user db service
 */
@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public User get(String id) {
        return em.find(User.class, id);
    }

    public boolean hasId(String id) {
        return em.find(User.class, id) != null;
    }

    public void add(User user) {
        em.persist(user);
    }

    public void remove(String id) {
        User user = em.find(User.class, id);
        System.out.println("Removed: " + user.toString());
        em.remove(user);
    }

    public List<User> getAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public List<User> search(User user) {
        LinkedList<String> conditions = new LinkedList<>();
        if (user.getId() != null) {
            conditions.add("u.id like '%" + user.getId() + "%'");
        }
        if (user.getPw() != null) {
            conditions.add("u.pw like '%" + user.getPw() + "%'");
        }
        if (user.getFirstName() != null) {
            conditions.add("u.firstName like '%" + user.getFirstName() + "%'");
        }
        if (user.getLastName() != null) {
            conditions.add("u.lastName like '%" + user.getLastName() + "%'");
        }
        if (user.getEmail() != null) {
            conditions.add("u.email like '%" + user.getEmail() + "%'");
        }
        String jpql = "select u from User u";
        Optional<String> conds = conditions.stream().reduce((s1, s2) -> s1 + " and " + s2);
        if (conds.isPresent() && !conds.get().isEmpty())
            jpql += " where " + conds.get();
        System.out.println("JPQL: " + jpql);
        return em.createQuery(jpql, User.class).getResultList();
    }

}
