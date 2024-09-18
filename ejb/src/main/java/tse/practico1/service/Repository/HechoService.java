package tse.practico1.service.Repository;

import jakarta.ejb.Stateless;
import tse.practico1.models.HechosModel;
import tse.practico1.service.Interface.IHechosLocal;
import tse.practico1.service.Interface.IHechosRemote;
import tse.practico1.models.Calificacion;
import tse.practico1.models.Estado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Stateless
public class HechoService implements IHechosLocal, IHechosRemote {
    private List<HechosModel> hechosList = new ArrayList<>();
    private AtomicInteger nextNumero = new AtomicInteger(1);

    @Override
    public void agregarHecho(Date fecha, String descripcion, Calificacion calificacion) {       
        if (fecha == null || descripcion == null || descripcion.isEmpty() || calificacion == null) {
            throw new IllegalArgumentException("Todos los campos deben estar completos.");
        }      
        HechosModel hecho = new HechosModel(nextNumero.getAndIncrement(), fecha, descripcion, calificacion, Estado.NUEVO); // Estado inicial es NUEVO
        hechosList.add(hecho);
    }

    @Override
    public List<HechosModel> getHechos() {
        return hechosList;
    }

    @Override
    public List<HechosModel> buscarHechos(String tipo, String buscar) {
        switch (tipo) {
            case "ID":
                int numero = Integer.parseInt(buscar);
                return hechosList.stream().filter(hecho -> hecho.getNumero() == numero).collect(Collectors.toList());
            case "Descripcion":
                return hechosList.stream().filter(hecho -> hecho.getDescripcion().equals(buscar)).collect(Collectors.toList());
            case "Fecha":
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaBuscada = formatter.parse(buscar);
                    return hechosList.stream().filter(hecho -> hecho.getFecha().equals(fechaBuscada)).collect(Collectors.toList());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            default:
                throw new RuntimeException("Tipo de búsqueda no válido.");
        }
    }

    @Override
    public boolean eliminarHecho(int numero) {
        Optional<HechosModel> optionalHecho = hechosList.stream().filter(hecho -> hecho.getNumero() == numero).findFirst();
        if (optionalHecho.isEmpty()) {
            return false;
        }
        hechosList.remove(optionalHecho.get());
        return true;
    }
}
