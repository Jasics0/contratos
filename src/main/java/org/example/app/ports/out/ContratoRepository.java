package org.example.app.ports.out;

import org.example.infraestructure.entities.ContratoDAO;

import java.util.List;

public interface ContratoRepository {

    ContratoDAO findById(long id);

    void save(ContratoDAO contratoDAO);

    void update(ContratoDAO contratoDAO);

    void delete(long id);

    List<ContratoDAO> findAll();
}
