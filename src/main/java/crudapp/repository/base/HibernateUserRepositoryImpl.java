package crudapp.repository.base;

import crudapp.model.Event;
import crudapp.model.User;
import crudapp.repository.*;
import crudapp.util.ConnectionHibernate;
import org.hibernate.Session;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HibernateUserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            users = session.createQuery("From User", User.class).list();
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            return users;
        }
    }

    @Override
    public User create(User user) {
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            return user;
        }
    }

    @Override
    public User getById(Integer integer) {
        User user = null;
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            user = session.get(User.class, integer);
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
        }finally {
            return user;
        }
    }

    @Override
    public User update(User user) {
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            return user;
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
