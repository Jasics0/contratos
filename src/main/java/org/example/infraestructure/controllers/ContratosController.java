package org.example.infraestructure.controllers;

import org.example.app.services.ContratoService;
import org.example.app.services.PlanService;
import org.example.infraestructure.entities.ContratoDAO;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class ContratosController {

    private Scanner sc = new Scanner(System.in);

    private static ContratoService contratoService;
    private static PlanService planService;

    public ContratosController(SessionFactory sessionFactory) {
        contratoService = new ContratoService(sessionFactory);
        planService = new PlanService(sessionFactory);
    }


    public void crearContrato() {
        System.out.println("Planes:");
        obtenerPlanes();
        System.out.print("Ingrese el id del plan:");
        int idPlan = sc.nextInt();

        System.out.println("Digite el documento del afiliado:");
        String documento = sc.next();

        System.out.print("Digite la cantidad de usuarios:");
        int cantidadUsuarios = sc.nextInt();

        System.out.print("Digite la fecha de registro:");
        String fechaRegistro = sc.next();

        System.out.print("Digite la fecha de inicio:");
        String fechaInicio = sc.next();

        String fechaRetiro = null;

        System.out.print("Digite la EPS:");
        String eps = sc.next();

        contratoService.crearContrato(new ContratoDAO(documento, idPlan, cantidadUsuarios, fechaInicio, fechaRetiro, fechaRegistro, eps));
    }

    public void eliminarContrato() {
        System.out.print("Digite el id del contrato:");
        int id = sc.nextInt();
        contratoService.eliminarContrato(id);
    }

    public void buscarContrato() {
        System.out.print("Digite el id del contrato:");
        int id = sc.nextInt();
        contratoService.buscarContrato(id);
    }



    public void obtenerPlanes() {
        planService.listarPlanes().forEach(System.out::println);
    }
}
