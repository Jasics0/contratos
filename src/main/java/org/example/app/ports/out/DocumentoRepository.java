package org.example.app.ports.out;

import org.example.infraestructure.entities.TipoDocumentoDAO;

import java.util.List;

public interface DocumentoRepository {

    TipoDocumentoDAO findById(long id);
    void save(TipoDocumentoDAO tipoDocumentoDAO);
    void update(TipoDocumentoDAO tipoDocumentoDAO);
    void delete(long id);
    List<TipoDocumentoDAO> findAll();

}
