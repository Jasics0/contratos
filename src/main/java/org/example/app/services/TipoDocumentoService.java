package org.example.app.services;

import org.example.app.ports.out.DocumentoRepository;
import org.example.infraestructure.controllers.dto.DocumentoDTO;
import org.example.infraestructure.entities.TipoDocumentoDAO;
import org.example.infraestructure.repositories.jpa.JpaDocumentoRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class TipoDocumentoService {
    private SessionFactory sessionFactory;

    private DocumentoRepository documentoRepository;

    public TipoDocumentoService(SessionFactory sessionFactory) {
        this.documentoRepository = new JpaDocumentoRepository(sessionFactory);
    }

    public List<DocumentoDTO> getDocumentosDTO() {
        List<DocumentoDTO> documentos = getTiposDocumento().stream()
                .map(tipoDocumento -> new DocumentoDTO((int) tipoDocumento.getTdcId(), tipoDocumento.getTdcNombre(), tipoDocumento.getTdcEstado()))
                .toList();

        return documentos;

    }

    public List<TipoDocumentoDAO> getTiposDocumento() {

        List<TipoDocumentoDAO> tiposDocumento = documentoRepository.findAll();

        return tiposDocumento;

    }

    public TipoDocumentoDAO getTipoDocumento(int id) {
        TipoDocumentoDAO tipoDocumentoDAO = documentoRepository.findById(id);
        return tipoDocumentoDAO;
    }

    public void crearDocumento(DocumentoDTO documentoDTO) {
        TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO(documentoDTO.getDocNombre(), 1);
        documentoRepository.save(tipoDocumentoDAO);
    }

    public void eliminarDocumento(int id) {
        documentoRepository.delete(id);
    }

    public void actualizarDocumento(DocumentoDTO documentoDTO) {
        TipoDocumentoDAO tipoDocumentoDAO = getTipoDocumento(documentoDTO.getDocId());
        tipoDocumentoDAO.setTdcNombre(documentoDTO.getDocNombre());
        tipoDocumentoDAO.setTdcEstado(documentoDTO.getDocEstado());
        System.out.println("actualizarDocumento: " + tipoDocumentoDAO);
        documentoRepository.update(tipoDocumentoDAO);
    }

}
