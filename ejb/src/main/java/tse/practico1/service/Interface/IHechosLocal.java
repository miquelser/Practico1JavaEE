package tse.practico1.service.Interface;


import jakarta.ejb.Local;
import tse.practico1.models.Clasificacion;
import tse.practico1.models.HechosModel;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Local
public interface IHechosLocal {
    void agregarHecho(LocalDate fecha, String descripcion, Clasificacion clasificacion);
    List<HechosModel> getHechos();
    List<HechosModel> buscarHechos(String buscar);
    boolean eliminarHecho(int numero);
}
