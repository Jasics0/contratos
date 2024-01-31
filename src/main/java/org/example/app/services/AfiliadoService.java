package org.example.app.services;

import org.example.app.ports.out.AfiliadoRepository;
import org.example.infraestructure.controllers.dto.AfiliadoDTO;
import org.example.infraestructure.entities.AfiliadoDAO;
import org.example.infraestructure.entities.TipoDocumentoDAO;
import org.example.infraestructure.repositories.jpa.JpaAfiliadoRepository;
import org.hibernate.SessionFactory;

import java.util.UUID;

public class AfiliadoService {

    private SessionFactory sessionFactory;
    private AfiliadoRepository afiliadoRepository;

    public AfiliadoService(SessionFactory sessionFactory) {
        this.afiliadoRepository = new JpaAfiliadoRepository(sessionFactory);
    }

    public void crearAfiliado(AfiliadoDTO afiliadoDTO) {

        AfiliadoDAO afiliadoDAO = new AfiliadoDAO();

        afiliadoDAO.setAfiId(UUID.randomUUID().toString().replace("-", ""));
        afiliadoDAO.setAfiNombre(afiliadoDTO.getNombre());
        afiliadoDAO.setAfiApellido(afiliadoDTO.getApellido());
        afiliadoDAO.setAfiDocumento(afiliadoDTO.getDocumento());
        afiliadoDAO.setAfiTelefono(afiliadoDTO.getTelefono());
        afiliadoDAO.setAfiMail(afiliadoDTO.getMail());
        afiliadoDAO.setAfiDireccion(afiliadoDTO.getDireccion());
        afiliadoDAO.setAfiEstado(afiliadoDTO.getEstado());


        TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
        tipoDocumentoDAO.setTdcId(afiliadoDTO.getIdDocumento());

        afiliadoDAO.setTdcId(tipoDocumentoDAO);

        try {
            afiliadoRepository.save(afiliadoDAO);
            System.out.println("Afiliado creado correctamente");
        } catch (Exception e) {
            System.out.println("Error al crear afiliado, verifique el tipo de documento.");
        }


    }

    public AfiliadoDTO actualizarAfiliado(AfiliadoDTO afiliado){
        AfiliadoDAO afiliadoDAO = afiliadoRepository.findById(afiliado.getDocumento());
        afiliadoDAO.setAfiNombre(afiliado.getNombre());
        afiliadoDAO.setAfiApellido(afiliado.getApellido());
        afiliadoDAO.setAfiTelefono(afiliado.getTelefono());
        afiliadoDAO.setAfiMail(afiliado.getMail());
        afiliadoDAO.setAfiDireccion(afiliado.getDireccion());
        afiliadoDAO.setAfiEstado(afiliado.getEstado());


        TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
        tipoDocumentoDAO.setTdcId(afiliado.getIdDocumento());

        afiliadoDAO.setTdcId(tipoDocumentoDAO);

        afiliadoRepository.update(afiliadoDAO);
        return afiliado;
    }

    public AfiliadoDTO getAfiliado(String id) {
        AfiliadoDAO afiliadoDAO = afiliadoRepository.findById(id);

        if (afiliadoDAO != null) {
            return new AfiliadoDTO(afiliadoDAO.getAfiNombre(), afiliadoDAO.getAfiApellido(), (int) afiliadoDAO.getTdcId().getTdcId(), afiliadoDAO.getAfiDocumento(), afiliadoDAO.getAfiTelefono(), afiliadoDAO.getAfiMail(), afiliadoDAO.getAfiDireccion(), afiliadoDAO.getAfiEstado());
        }
        return null;
    }

    public void deleteAfiliado(String id) {

        AfiliadoDAO afiliadoDAO = afiliadoRepository.findById(id);

        try {
            afiliadoRepository.delete(afiliadoDAO.getAfiId());
            System.out.println("Afiliado eliminado correctamente");
        }catch (Exception e){
            System.out.println("Error al eliminar afiliado, verifique el documento.");
        }

    }

}
