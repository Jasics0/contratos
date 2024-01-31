package org.example.infraestructure.repositories.jpa;

import org.example.app.ports.out.ContratoRepository;
import org.example.infraestructure.entities.ContratoDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class JpaContratoRepository implements ContratoRepository {

    private final SessionFactory sessionFactory;

    public JpaContratoRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ContratoDAO findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ContratoDAO.class, id);
        }
    }

    @Override
    public void save(ContratoDAO contratoDAO) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(contratoDAO);
            tx.commit();
        }
    }


    @Override
    public void update(ContratoDAO contratoDAO) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(contratoDAO);
            tx.commit();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            ContratoDAO contratoDAO = session.get(ContratoDAO.class, id);
            if (contratoDAO != null) {
                session.delete(contratoDAO);
            }
            tx.commit();
        }
    }

    @Override
    public List<ContratoDAO> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ContratoDAO", ContratoDAO.class).list();
        }
    }
}
