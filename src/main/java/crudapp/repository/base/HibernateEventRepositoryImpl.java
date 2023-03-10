package crudapp.repository.base;

import crudapp.model.Event;
import crudapp.model.File;
import crudapp.repository.*;
import crudapp.util.ConnectionHibernate;
import org.hibernate.Session;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class HibernateEventRepositoryImpl implements EventRepository {
    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            events = session.createQuery("From Event", Event.class).list();
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            return events;
        }
    }

    @Override
    public Event create(Event event) {
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            session.beginTransaction();
            session.save(event);
            session.getTransaction().commit();
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            return event;
        }
    }

    @Override
    public Event getById(Integer integer) {
        Event event = null;
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            event = session.get(Event.class, integer);
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
        }finally {
            return event;
        }
    }

    @Override
    public Event update(Event event) {
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            session.beginTransaction();
            session.update(event);
            session.getTransaction().commit();
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            return event;
        }
    }

    @Override
    public void delete(Integer integer) {
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            session.beginTransaction();
            session.remove(getById(integer));
            session.getTransaction().commit();
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
