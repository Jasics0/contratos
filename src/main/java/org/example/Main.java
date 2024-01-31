package org.example;

import org.example.infraestructure.controllers.AfiliadoController;
import org.example.infraestructure.controllers.ContratosController;
import org.example.infraestructure.controllers.DocumentoController;
import org.example.infraestructure.controllers.PlanController;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    private static SessionFactory sessionFactory;

    private static AfiliadoController afiliadoController;

    private static DocumentoController tipoDocumentoController;

    private static PlanController planController;

    private static ContratosController contratosController;


    private static void init() {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        afiliadoController = new AfiliadoController(sessionFactory);
        tipoDocumentoController = new DocumentoController(sessionFactory);
        planController = new PlanController(sessionFactory);
        contratosController = new ContratosController(sessionFactory);

    }

    public static void main(String[] args) {

        init();


        int opc = 0;


        while (opc != 5) {

            menu();
            opc = getOpc();

            switch (opc) {
                case 1:
                    menuAfiliados();
                    break;
                case 2:
                    menuTipoDocumentos();
                    break;
                case 3:
                    menuPlanes();
                    break;
                case 4:
                    menuContratos();
                    break;

                default:
                    System.out.println("Opcion no valida");
                    break;

            }
        }

    }

    private static void menu() {
        System.out.println("1. Afiliados");
        System.out.println("2. Tipos de Documento");
        System.out.println("3. Planes");
        System.out.println("4. Contratos");
        System.out.println("5. Salir");
        System.out.println();
    }

    private static void menuAfiliados() {
        int opc = 0;

        while (opc != 5) {

            System.out.println("1. Crear Afiliado");
            System.out.println("2. Obtener Afiliado");
            System.out.println("3. Eliminar Afiliado");
            System.out.println("4. Actualizar Afiliado");
            System.out.println("5. Salir");
            System.out.println();

            opc = getOpc();

            switch (opc) {
                case 1:
                    afiliadoController.crearAfiliado();
                    break;
                case 2:
                    afiliadoController.obtenerAfiliado();
                    break;
                case 3:
                    afiliadoController.eliminarAfiliado();
                    break;
                case 4:
                    afiliadoController.actualizarAfiliado();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    private static void menuTipoDocumentos(){
        int opc = 0;

        while (opc != 6) {

            System.out.println("1. Crear Tipo de Documento");
            System.out.println("2. Obtener Tipo de Documento");
            System.out.println("3. Eliminar Tipo de Documento");
            System.out.println("4. Actualizar Tipo de Documento");
            System.out.println("5. Obtener todos los Tipos de Documento");
            System.out.println("6. Salir");
            System.out.println();

            opc = getOpc();

            switch (opc) {
                case 1:
                    tipoDocumentoController.crearDocumento();
                    break;
                case 2:
                    tipoDocumentoController.obtenerDocumento();
                    break;
                case 3:
                    tipoDocumentoController.eliminarDocumento();
                    break;
                case 4:
                    tipoDocumentoController.actualizarDocumento();
                    break;
                case 5:
                    tipoDocumentoController.obtenerDocumentos();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }

    }

    private static void menuPlanes(){
        int opc = 0;

        while (opc != 6) {

            System.out.println("1. Crear Plan");
            System.out.println("2. Obtener Plan");
            System.out.println("3. Eliminar Plan");
            System.out.println("4. Actualizar Plan");
            System.out.println("5. Obtener todos los Planes");
            System.out.println("6. Salir");
            System.out.println();

            opc = getOpc();

            switch (opc) {
                case 1:
                    planController.crearPlan();
                    break;
                case 2:
                    planController.obtenerPlan();
                    break;
                case 3:
                    planController.eliminarPlan();
                    break;
                case 4:
                    planController.actualizarPlan();
                    break;
                case 5:
                    planController.obtenerPlanes();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    private static void menuContratos(){
        int opc = 0;

        while (opc != 4) {

            System.out.println("1. Crear Contrato");
            System.out.println("2. Obtener Contrato");
            System.out.println("3. Eliminar Contrato");
            System.out.println("4. Salir");
            System.out.println();

            opc = getOpc();

            switch (opc) {
                case 1:
                    contratosController.crearContrato();
                    break;
                case 2:
                    contratosController.buscarContrato();
                    break;
                case 3:
                    contratosController.eliminarContrato();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    private static int getOpc() {

        System.out.print("Ingrese el numero de la opcion que desea realizar:");
        return sc.nextInt();
    }


}