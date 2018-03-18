package dao;

import entity.City;
import entity.Flight;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CityDAO {

    private SessionFactory factory;

    public CityDAO(SessionFactory factory){
        this.factory = factory;
    }

    public City findCityByName(String name){
        Session session = factory.openSession();
        Transaction tx = null;
        List<City> cities = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM City WHERE name = :name", City.class);
            query.setParameter("name", name);
            cities = query.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return cities.get(0);
    }
}
