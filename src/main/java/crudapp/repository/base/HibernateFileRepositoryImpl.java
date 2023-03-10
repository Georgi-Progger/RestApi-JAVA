package crudapp.repository.base;

import crudapp.model.File;
import crudapp.repository.*;
import crudapp.util.ConnectionHibernate;
import org.hibernate.Session;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HibernateFileRepositoryImpl implements FileRepository {
    @Override
    public List<File> getAll() {
        List<File> files = new ArrayList<>();
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            files = session.createQuery("From File", File.class).list();
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            return files;
        }
    }

    @Override
    public File create(File file) {
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            session.beginTransaction();
            session.save(file);
            session.getTransaction().commit();
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            return file;
        }
    }

    @Override
    public File getById(Integer integer) {
        File file = null;
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            file = session.get(File.class, integer);
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
        }finally {
            return file;
        }
    }

    @Override
    public File update(File file) {
        try (Session session = ConnectionHibernate.getConnectSession().getSession().openSession();) {
            session.beginTransaction();
            session.update(file);
            session.getTransaction().commit();
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            return file;
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
