package in.purabtech.app;

import java.util.List;

import in.purabtech.app.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
    	
    	Configuration cfg = new Configuration();
    	
    	cfg.configure("hibernate.cfg.xml");
    	SessionFactory factory = cfg.buildSessionFactory();
    	
    	factory.getCurrentSession();
    	
		try {
			persist(factory);
			load(factory);
		} finally {
			//factory.close();
		}

        
    }
    
    private static void load(SessionFactory sessionFactory) {
        System.out.println("-- loading users --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<User> persons = session.createQuery("FROM User").list();
        persons.forEach((x) -> System.out.printf("- %s%n", x));
        session.close();
    }

    private static void persist(SessionFactory sessionFactory) {
        User p1 = new User("Purab", "Kharat", "purabdk@purabtech.in");
        User p2 = new User("Neha", "Kharat", "nehakharat@purabtech.in");
        System.out.println("-- persisting users --");
        System.out.printf("- %s%n- %s%n", p1, p2);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(p1);
        session.save(p2);
        session.getTransaction().commit();
    }
}