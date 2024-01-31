package org.example.infraestructure.controllers;

import org.example.app.services.AfiliadoService;
import org.example.app.services.TipoDocumentoService;
import org.example.infraestructure.controllers.dto.AfiliadoDTO;
import org.example.infraestructure.controllers.dto.DocumentoDTO;
import org.example.infraestructure.entities.AfiliadoDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class AfiliadoController {

    private Scanner sc = new Scanner(System.in);

    private static TipoDocumentoService tipoDocumentoService;

    private static AfiliadoService afiliadoService;

    public AfiliadoController(SessionFactory sessionFactory) {
        tipoDocumentoService = new TipoDocumentoService(sessionFactory);
        afiliadoService = new AfiliadoService(sessionFactory);
    }

    public void crearAfiliado() {

        int idDocumento = getDocumento();

        System.out.print("Ingrese el documento:");
        String documento = sc.next();

        System.out.print("Ingrese el nombre:");
        String nombre = sc.next();

        System.out.print("Ingrese el apellido:");
        String apellido = sc.next();

        System.out.print("Ingrese el telefono:");
        String telefono = sc.next();

        System.out.print("Ingrese el mail:");
        String mail = sc.next();

        System.out.print("Ingrese la direccion:");
        String direccion = sc.next();


        AfiliadoDTO afiliadoDTO = new AfiliadoDTO(nombre, apellido, idDocumento, documento, telefono, mail, direccion, 1);

        afiliadoService.crearAfiliado(afiliadoDTO);
    }

    public void obtenerAfiliado() {

        System.out.print("\nIngrese el documento del afiliado:");
        String id = sc.next();

        AfiliadoDTO afiliado = afiliadoService.getAfiliado(id);

        System.out.println();

        if (afiliado != null) {
            System.out.println(afiliado);
        } else {
            System.out.println("No se encontro el afiliado");
        }
    }

    public void eliminarAfiliado() {

        System.out.print("\nIngrese el documento del afiliado:");
        String id = sc.next();

        afiliadoService.deleteAfiliado(id);

    }

    public void actualizarAfiliado() {

        int idDocumento = getDocumento();

        System.out.print("\nIngrese el documento del afiliado:");
        String id = sc.next();
        System.out.print("\nIngrese el nombre:");
        String nombre = sc.next();
        System.out.print("\nIngrese el apellido:");
        String apellido = sc.next();
        System.out.print("\nIngrese el telefono:");
        String telefono = sc.next();
        System.out.print("\nIngrese el mail:");
        String mail = sc.next();
        System.out.print("\nIngrese la direccion:");
        String direccion = sc.next();
        System.out.print("\nIngrese el estado:");
        int estado = sc.nextInt();

        AfiliadoDTO afiliado = new AfiliadoDTO(nombre, apellido, idDocumento, id, telefono, mail, direccion, estado);

        afiliadoService.actualizarAfiliado(afiliado);
    }

    private int getDocumento() {
        System.out.println();

        List<DocumentoDTO> documentos = tipoDocumentoService.getDocumentosDTO();

        for (DocumentoDTO tipoDocumento : documentos) {
            System.out.println(tipoDocumento.getDocId() + ". " + tipoDocumento.getDocNombre());
        }

        System.out.print("\nIngrese el tipo de documento:");
        return sc.nextInt();
    }

}
