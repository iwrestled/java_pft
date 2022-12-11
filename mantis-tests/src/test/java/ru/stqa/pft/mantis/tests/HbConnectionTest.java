package ru.stqa.pft.mantis.tests;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test
    public void testHbConnection(){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        List<UserData> result = session.createQuery("from UserData where id=(select max(id) from UserData" ).list();
        Query query = session.createQuery("from UserData  order by id desc");
        query.setMaxResults(1);
        UserData userData = (UserData) query.getSingleResult();
        session.getTransaction().commit();
        session.close();

        System.out.println(userData);
//        for ( UserData contact : result.list() ) {
//            System.out.println(contact);
        }
}
