package org.example.infraestructure.controllers;

import org.example.app.services.TipoDocumentoService;
import org.example.infraestructure.controllers.dto.DocumentoDTO;
import org.example.infraestructure.entities.TipoDocumentoDAO;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class DocumentoController {
    private Scanner sc = new Scanner(System.in);

    private static TipoDocumentoService tipoDocumentoService;

    public DocumentoController(SessionFactory sessionFactory) {
        tipoDocumentoService = new TipoDocumentoService(sessionFactory);
    }

    public void crearDocumento() {
        System.out.print("Ingrese el tipo de documento:");
        String nombre = sc.next();

        DocumentoDTO documentoDTO = new DocumentoDTO();
        documentoDTO.setDocNombre(nombre);

        tipoDocumentoService.crearDocumento(documentoDTO);

    }

    public void actualizarDocumento() {

        System.out.print("Ingrese el id del documento:");
        int id = sc.nextInt();
        System.out.print("Ingrese el nuevo nombre del documento:");
        String nombre = sc.next();
        System.out.println("Ingrese el nuevo estado del documento:");
        int estado = sc.nextInt();

        DocumentoDTO documentoDTO = new DocumentoDTO();
        documentoDTO.setDocId(id);
        documentoDTO.setDocNombre(nombre);
        documentoDTO.setDocEstado(estado);

        tipoDocumentoService.actualizarDocumento(documentoDTO);
    }

    public void eliminarDocumento() {
        System.out.print("Ingrese el id del documento:");
        int id = sc.nextInt();

        tipoDocumentoService.eliminarDocumento(id);
    }

    public void obtenerDocumento() {
        System.out.print("Ingrese el id del documento:");
        int id = sc.nextInt();

        TipoDocumentoDAO tipoDocumentoDAO = tipoDocumentoService.getTipoDocumento(id);

        DocumentoDTO documentoDTO = new DocumentoDTO((int) tipoDocumentoDAO.getTdcId(), tipoDocumentoDAO.getTdcNombre(), tipoDocumentoDAO.getTdcEstado());

        System.out.println(documentoDTO);
    }

    public void obtenerDocumentos() {
        System.out.println("Lista de documentos:");
        for (DocumentoDTO documento : tipoDocumentoService.getDocumentosDTO()) {
            System.out.println(documento);
        }
    }

}
