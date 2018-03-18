package dao;

import entity.Flight;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@SuppressWarnings("unchecked")
public class FlightDAO {

    private SessionFactory factory;

    public FlightDAO(SessionFactory factory){
        this.factory = factory;
    }

    public void addFlight(Flight flight){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(flight);
            tx.commit();
        }catch (HibernateException e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    public void deleteFlight(Flight flight){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(flight);
            tx.commit();
        }catch (HibernateException e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    public void updateFlight(Flight flight){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(flight);
            tx.commit();
        }catch (HibernateException e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    public Flight findFlightByFlightId(String flightId){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Flight> flights = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Flight WHERE fligthId = :flightId", Flight.class);
            query.setParameter("flightId", flightId);
            flights = query.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return flights.get(0);
    }

    public List<Flight> findFlights(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Flight> flights = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Flight");
            flights = query.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return flights;
    }
}
