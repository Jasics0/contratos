package org.example.app.ports.out;

import org.example.infraestructure.entities.PlanDAO;

import java.util.List;

public interface PlanRepository {
    PlanDAO findById(long id);

    void save(PlanDAO planDAORepository);

    void update(PlanDAO planDAORepository);

    void delete(long id);

    List<PlanDAO> findAll();
}
