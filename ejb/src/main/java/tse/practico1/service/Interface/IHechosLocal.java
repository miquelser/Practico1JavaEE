package tse.practico1.service.Interface;


import jakarta.ejb.Local;
import tse.practico1.models.HechosModel;
import tse.practico1.models.Calificacion;

import java.util.Date;
import java.util.List;

@Local
public interface IHechosLocal {
    void agregarHecho(Date fecha, String descripcion, Calificacion calificacion);
    List<HechosModel> getHechos();
    List<HechosModel> buscarHechos(String buscar);
    boolean eliminarHecho(int numero);
}
