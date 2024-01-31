package org.example.infraestructure.repositories.jpa;

import org.example.app.ports.out.AfiliadoRepository;
import org.example.infraestructure.entities.AfiliadoDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class JpaAfiliadoRepository implements AfiliadoRepository {
    private final SessionFactory sessionFactory;

    public JpaAfiliadoRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public AfiliadoDAO findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM AfiliadoDAO WHERE afiDocumento=:id", AfiliadoDAO.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    @Override
    public void save(AfiliadoDAO afiliadoDAO) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(afiliadoDAO);
            tx.commit();
        }
    }

    @Override
    public void update(AfiliadoDAO afiliadoDAO) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(afiliadoDAO);
            tx.commit();
        }
    }

    @Override
    public void delete(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            AfiliadoDAO afiliadoDAO = session.get(AfiliadoDAO.class, id);
            if (afiliadoDAO != null) {
                session.delete(afiliadoDAO);
            }
            tx.commit();
        }
    }

    @Override
    public List<AfiliadoDAO> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM AfiliadoDAO", AfiliadoDAO.class).list();
        }
    }

}
