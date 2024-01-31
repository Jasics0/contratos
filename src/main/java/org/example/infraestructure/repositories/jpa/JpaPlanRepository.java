package org.example.infraestructure.repositories.jpa;

import org.example.app.ports.out.PlanRepository;
import org.example.infraestructure.entities.PlanDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class JpaPlanRepository implements PlanRepository {

    private final SessionFactory sessionFactory;

    public JpaPlanRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PlanDAO findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(PlanDAO.class, id);
        }
    }

    @Override
    public void save(PlanDAO planDAO) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(planDAO);
            tx.commit();
        }
    }

    @Override
    public void update(PlanDAO planDAO) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(planDAO);
            tx.commit();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            PlanDAO planDAO = session.get(PlanDAO.class, id);
            if (planDAO != null) {
                Transaction tx = session.beginTransaction();
                session.delete(planDAO);
                tx.commit();
            }
        }
    }

    @Override
    public java.util.List<PlanDAO> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM PlanDAO WHERE plnEstado=1", PlanDAO.class).list();
        }
    }
}
