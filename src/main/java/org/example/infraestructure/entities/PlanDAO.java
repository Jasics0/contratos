package org.example.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "plan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long plnId;

    @Column(name = "pln_nombre")
    private String plnNombre;

    private LocalDate plnFechaInicio;

    private LocalDate plnFechaFin;

    @Column(name = "pln_estado")
    private int plnEstado;

    public PlanDAO(String nombre, String fechaInicio, String fechaFin) {
        this.plnNombre = nombre;
        this.plnFechaInicio = LocalDate.parse(fechaInicio);
        this.plnFechaFin = LocalDate.parse(fechaFin);
        this.plnEstado = 1;
    }

}
