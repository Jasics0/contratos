package org.example.app.services;

import org.example.app.ports.out.AfiliadoRepository;
import org.example.app.ports.out.ContratoRepository;
import org.example.app.ports.out.PlanRepository;
import org.example.infraestructure.entities.AfiliadoDAO;
import org.example.infraestructure.entities.ContratoDAO;
import org.example.infraestructure.entities.PlanDAO;
import org.example.infraestructure.repositories.jpa.JpaAfiliadoRepository;
import org.example.infraestructure.repositories.jpa.JpaContratoRepository;
import org.example.infraestructure.repositories.jpa.JpaPlanRepository;
import org.hibernate.SessionFactory;

public class ContratoService {

    private SessionFactory sessionFactory;

    private ContratoRepository contratoRepository;
    private AfiliadoRepository afiliadoRepository;

    private PlanRepository planRepository;

    public ContratoService(SessionFactory sessionFactory) {
        this.contratoRepository = new JpaContratoRepository(sessionFactory);
        this.afiliadoRepository = new JpaAfiliadoRepository(sessionFactory);
        this.planRepository = new JpaPlanRepository(sessionFactory);
    }

    public void crearContrato(ContratoDAO contratoDAO) {

        AfiliadoDAO afiliadoDAO = afiliadoRepository.findById(contratoDAO.getAfiId().getAfiDocumento());
        PlanDAO planDAO= planRepository.findById(contratoDAO.getPlnId().getPlnId());

        contratoDAO.setAfiId(afiliadoDAO);
        contratoDAO.setPlnId(planDAO);

        contratoRepository.save(contratoDAO);

        System.out.println("Contrato creado");
    }

    public void eliminarContrato(int id) {
        contratoRepository.delete(id);

        System.out.println("Contrato eliminado");
    }

    public ContratoDAO buscarContrato(int id) {
        ContratoDAO contratoDAO = contratoRepository.findById(id);

        System.out.println("Contrato encontrado");

        System.out.println(contratoDAO);

        return contratoDAO;
    }



}
