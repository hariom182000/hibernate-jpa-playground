package org.example;

import Entities.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import static jakarta.persistence.Persistence.createEntityManagerFactory;
import static org.hibernate.cfg.AvailableSettings.JAKARTA_HBM2DDL_DATABASE_ACTION;
import static org.hibernate.tool.schema.Action.UPDATE;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Map<String, org.hibernate.tool.schema.Action> properties = new HashMap<>();
        properties.put(JAKARTA_HBM2DDL_DATABASE_ACTION, UPDATE);
        EntityManagerFactory factory = createEntityManagerFactory("example",
                                                                  properties);
        EntityManager em1 = factory.createEntityManager();
        EntityManager em2 = factory.createEntityManager();

        try {
            em1.getTransaction().begin();
            Book p = new Book();
            //em1.persist(p);
            Book b1=em1.find(Book.class,1L);
            Book b2=em2.find(Book.class,1L);
            System.out.println(b1);
            System.out.println(b2);



            em1.getTransaction().begin();
            b1=em1.find(Book.class,1L);
            System.out.println(b1);
            em1.getTransaction().commit();
            em1.getTransaction().commit();
        } finally {
            em1.close();
            em2.close();
        }
    }
}