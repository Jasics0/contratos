package org.example.infraestructure.controllers;

import org.example.app.services.PlanService;
import org.example.infraestructure.entities.PlanDAO;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

public class PlanController {

    private Scanner sc = new Scanner(System.in);

    private static PlanService planService;

    public PlanController(SessionFactory sessionFactory) {
        planService = new PlanService(sessionFactory);
    }

    public void crearPlan(){
        System.out.println("Ingrese el nombre del plan:");
        String nombre = sc.next();
        System.out.println("Fecha de inicio:");
        String fechaInicio = sc.next();
        System.out.println("Fecha de fin:");
        String fechaFin = sc.next();

        PlanDAO planDAO = new PlanDAO(nombre, fechaInicio, fechaFin);

        planService.crearPlan(planDAO);
    }

    public void obtenerPlan(){
        System.out.println("Ingrese el id del plan:");
        int id = sc.nextInt();
        PlanDAO planDAO = planService.getPlan(id);
        System.out.println(planDAO);
    }

    public void   eliminarPlan(){
        System.out.println("Ingrese el id del plan:");
        int id = sc.nextInt();
        planService.eliminarPlan(id);
    }

    public void obtenerPlanes(){
        planService.listarPlanes().forEach(System.out::println);
    }

    public void actualizarPlan(){
        System.out.println("Ingrese el id del plan:");
        int id = sc.nextInt();
        PlanDAO planDAO = planService.getPlan(id);
        System.out.println("Ingrese el nombre del plan:");
        String nombre = sc.next();
        System.out.println("Fecha de inicio:");
        String fechaInicio = sc.next();
        System.out.println("Fecha de fin:");
        String fechaFin = sc.next();

        planDAO.setPlnNombre(nombre);
        planDAO.setPlnFechaInicio(LocalDate.parse(fechaInicio));
        planDAO.setPlnFechaFin(LocalDate.parse(fechaFin));

        planService.actualizarPlan(planDAO);
    }
}
