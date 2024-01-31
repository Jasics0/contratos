package org.example.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "contrato")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratoDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ctoId;

    @ManyToOne
    @JoinColumn(name = "afi_id")
    private AfiliadoDAO afiId;

    @ManyToOne
    @JoinColumn(name = "pln_id")
    private PlanDAO plnId;

    private int ctoCantidadUsuarios;

    private LocalDate ctoFechaInicio;

    private LocalDate ctoFechaRetiro;

    private LocalDate ctoFechaRegistro;

    private String ctoEps;

    public ContratoDAO(String documento, int idPlan,int cantidadUsuarios, String fechaInicio,String fechaRetiro,String fechaRegistro,String eps) {
        AfiliadoDAO afiliadoDAO = new AfiliadoDAO();
        afiliadoDAO.setAfiDocumento(documento);

        PlanDAO planDAO = new PlanDAO();
        planDAO.setPlnId(idPlan);

    	this.afiId=afiliadoDAO;
    	this.plnId=planDAO;
    	this.ctoCantidadUsuarios=cantidadUsuarios;
    	this.ctoFechaInicio=LocalDate.parse(fechaInicio);
    	this.ctoFechaRetiro=(fechaRetiro==null)?null:LocalDate.parse(fechaRetiro);
    	this.ctoFechaRegistro=LocalDate.parse(fechaRegistro);
    	this.ctoEps=eps;
    }
}
