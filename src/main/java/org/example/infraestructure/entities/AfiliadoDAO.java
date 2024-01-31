package org.example.infraestructure.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
@Entity
@Table(name = "afiliado",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"tdc_id", "afiDocumento"})})
@Data
@ToString

public class AfiliadoDAO {

    @Id
    private String afiId;

    private String afiNombre;
    private String afiApellido;

    @ManyToOne
    @JoinColumn(name = "tdc_id")
    private TipoDocumentoDAO tdcId;

    private String afiDocumento;
    private String afiTelefono;
    private String afiMail;
    private String afiDireccion;
    private int afiEstado;
}
