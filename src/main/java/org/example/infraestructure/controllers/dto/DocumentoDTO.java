package org.example.infraestructure.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {

    int docId;

    String docNombre;

    int docEstado;

    public String toString() {
        return "DocumentoDTO{" +
                "docId=" + docId +
                ", docNombre='" + docNombre + '\'' +
                ", docEstado=" + docEstado +
                '}';
    }

}
