package dao;



import entity.User;
import org.hibernate.*;
import org.hibernate.query.Query;


import java.util.List;

@SuppressWarnings("unchecked")
public class UserDAO {
    private SessionFactory factory;

    public UserDAO(SessionFactory factory){
        this.factory = factory;
    }

    public User findByUsername(String username){
        Session session = factory.openSession();
        Transaction tx = null;
        List<User> users = null;
        try {
            tx = session.beginTransaction();
            Query query;
            query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            users = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return users != null && !users.isEmpty() ? users.get(0) : null;
    }
}
