package tse.practico1.service.Interface;


import jakarta.ejb.Remote;
import tse.practico1.models.HechosModel;
import tse.practico1.models.Calificacion;

import java.util.Date;
import java.util.List;

@Remote
public interface IHechosRemote {
    void agregarHecho(Date fecha, String descripcion, Calificacion calificacion);
    List<HechosModel> getHechos();
    List<HechosModel> buscarHechos(String tipo, String buscar);
    boolean eliminarHecho(int numero);
}
