package org.example.infraestructure.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_documento")
@Data
@NoArgsConstructor
public class TipoDocumentoDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tdcId;

    @Column(name = "tdc_nombre")
    private String tdcNombre;

    @Column(name = "tdc_estado")
    private int tdcEstado;

    public TipoDocumentoDAO(String tdcNombre, int tdcEstado) {
        this.tdcNombre = tdcNombre;
        this.tdcEstado = tdcEstado;
    }

}
