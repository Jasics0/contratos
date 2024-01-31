package org.example.infraestructure.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AfiliadoDTO {

    private String nombre;
    private String apellido;
    private int idDocumento;
    private String documento;
    private String telefono;
    private String mail;
    private String direccion;
    private int estado;

    public String toString() {
        return "AfiliadoDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", idDocumento='" + idDocumento + '\'' +
                ", documento='" + documento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", mail='" + mail + '\'' +
                ", direccion='" + direccion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
