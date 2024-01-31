package org.example.infraestructure.repositories.jpa;

import org.example.app.ports.out.DocumentoRepository;
import org.example.infraestructure.entities.TipoDocumentoDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class JpaDocumentoRepository implements DocumentoRepository {

    private final SessionFactory sessionFactory;

    public JpaDocumentoRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TipoDocumentoDAO findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(TipoDocumentoDAO.class, id);
        }
    }

    @Override
    public void save(TipoDocumentoDAO tipoDocumentoDAO) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(tipoDocumentoDAO);
            tx.commit();
        }
    }

    @Override
    public void update(TipoDocumentoDAO tipoDocumentoDAO) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(tipoDocumentoDAO);
            tx.commit();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            TipoDocumentoDAO tipoDocumentoDAO = session.get(TipoDocumentoDAO.class, id);
            tipoDocumentoDAO.setTdcEstado(0);
            if (tipoDocumentoDAO != null) {
                Transaction tx = session.beginTransaction();
                session.update(tipoDocumentoDAO);
                tx.commit();
            }
        }
    }

    @Override
    public java.util.List<TipoDocumentoDAO> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM TipoDocumentoDAO WHERE tdcEstado=1", TipoDocumentoDAO.class).list();
        }
    }
}
