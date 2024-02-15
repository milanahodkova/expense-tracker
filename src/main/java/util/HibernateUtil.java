package util;


import entity.Expense;
import entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();

            properties.put(Environment.DRIVER, PropertiesUtil.get("hibernate.connection.driver_class"));
            properties.put(Environment.URL, PropertiesUtil.get("hibernate.connection.url"));
            properties.put(Environment.USER, PropertiesUtil.get("hibernate.connection.username"));
            properties.put(Environment.PASS, PropertiesUtil.get("hibernate.connection.password"));
            properties.put(Environment.DIALECT, PropertiesUtil.get("hibernate.dialect"));
            properties.put(Environment.HBM2DDL_AUTO, PropertiesUtil.get("hibernate.hbm2ddl.auto"));
            properties.put(Environment.SHOW_SQL, PropertiesUtil.get("hibernate.show_sql"));

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Expense.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}