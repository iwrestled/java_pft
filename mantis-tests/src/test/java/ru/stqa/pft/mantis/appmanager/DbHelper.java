package ru.stqa.pft.mantis.appmanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.util.List;

public class DbHelper {

    private ApplicationManager app;

    private final SessionFactory sessionFactory;


//старое оформление DBhelper, новое по аналогии с другими хелперами
/*        public DbHelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

 */

        public DbHelper(ApplicationManager app){
            // A SessionFactory is set up once for an application!
            this.app = app;
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }


/*   public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }
    public ContactData getContactById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ContactData contact = (ContactData) session
                .createQuery("from ContactData where id = :id")
                .setParameter("id", id)
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return contact;

    }
    public GroupData getGroupById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData contact = (GroupData) session
                .createQuery("from GroupData where id = :id")
                .setParameter("id", id)
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return contact;

    }

    public  getUserData(int id,String user,String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData contact = (GroupData) session
                .createQuery("from GroupData where id = :id")
                .setParameter("id", id)
                .
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return contact;

    }

 */
}
