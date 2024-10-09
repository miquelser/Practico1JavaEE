package tse.practico1.service.Repository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import tse.practico1.models.HechosModel;
import tse.practico1.service.Interface.IHechosLocal;
import tse.practico1.service.Interface.IHechosRemote;
import tse.practico1.models.Clasificacion;
import tse.practico1.singleton.HechoSingleton;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Stateless
public class HechosService implements IHechosLocal, IHechosRemote {

@EJB
private HechoSingleton hechoSingleton;

@Override
public void agregarHecho(LocalDate fecha, String descripcion, Clasificacion clasificacion) {
    hechoSingleton.agregarHecho(fecha,descripcion, clasificacion);
}

@Override
public List<HechosModel> getHechos() {
    return hechoSingleton.getHechos();
}

@Override
public List<HechosModel> buscarHechos(String buscar) {
   return hechoSingleton.buscarHechos(buscar);
}

@Override
public boolean eliminarHecho(int numero) {
 return hechoSingleton.eliminarHecho(numero);
}



    }
