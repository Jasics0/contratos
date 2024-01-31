package org.example.app.ports.out;

import org.example.infraestructure.entities.AfiliadoDAO;

import java.util.List;
import java.util.UUID;

public interface AfiliadoRepository {

    AfiliadoDAO findById(String id);
    void save(AfiliadoDAO afiliadoDAO);
    void update(AfiliadoDAO afiliadoDAO);
    void delete(String afiliado);

    List<AfiliadoDAO> findAll();
}
