package org.example.app.services;

import org.example.app.ports.out.PlanRepository;
import org.example.infraestructure.entities.PlanDAO;
import org.example.infraestructure.repositories.jpa.JpaPlanRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class PlanService {
    private SessionFactory sessionFactory;

    private PlanRepository planRepository;

    public PlanService(SessionFactory sessionFactory) {
        this.planRepository = new JpaPlanRepository(sessionFactory);
    }

    public void crearPlan(PlanDAO planDAO) {
        planRepository.save(planDAO);
        System.out.println("Plan creado con exito");
    }

    public PlanDAO getPlan(int id) {
        return planRepository.findById(id);
    }

    public void actualizarPlan(PlanDAO planDAO) {
        planRepository.update(planDAO);
        System.out.println("Plan actualizado con exito");
    }

    public void eliminarPlan(int id) {
        planRepository.delete(id);
        System.out.println("Plan eliminado con exito");
    }

    public List<PlanDAO> listarPlanes() {
       return planRepository.findAll();
    }


}
