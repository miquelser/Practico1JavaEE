package tse.practico1.service.Interface;


import jakarta.ejb.Remote;
import tse.practico1.models.Clasificacion;
import tse.practico1.models.HechosModel;

import java.util.Date;
import java.util.List;

@Remote
public interface IHechosRemote {
    void agregarHecho(Date fecha, String descripcion, Clasificacion clasificacion);
    List<HechosModel> getHechos();
    List<HechosModel> buscarHechos(String buscar);
    boolean eliminarHecho(int numero);
}
