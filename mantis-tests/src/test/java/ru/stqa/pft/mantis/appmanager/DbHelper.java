package ru.stqa.pft.mantis.appmanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.stqa.pft.mantis.model.UserData;


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



    public UserData getUserInfo() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        List<UserData> result = session.createQuery("from UserData where id=(select max(id) from UserData" ).list();
        Query query = session.createQuery("from UserData  order by id desc");
        query.setMaxResults(1);
        UserData userData = (UserData) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return userData;
    }

/*    public  getUserData(int id,String user,String email) {
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

 */
}
