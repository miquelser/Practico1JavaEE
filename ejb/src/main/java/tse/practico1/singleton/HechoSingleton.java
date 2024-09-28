package tse.practico1.singleton;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import tse.practico1.models.HechosModel;
import tse.practico1.models.Calificacion;
import tse.practico1.models.Estado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Singleton
@Startup
@Lock(LockType.READ)
public class HechoSingleton{
    private List<HechosModel> hechosList = new ArrayList<>();
    private AtomicInteger nextNumero = new AtomicInteger(1);

@Lock(LockType.WRITE)
public void agregarHecho(Date fecha, String descripcion, Calificacion calificacion) {
    int numero = nextNumero.getAndIncrement();
    HechosModel hecho = new HechosModel(numero, fecha, descripcion, calificacion, Estado.NUEVO);
    hechosList.add(hecho);
    System.out.println("Hecho agregado: " + hecho.getDescripcion() + " en la fecha " + hecho.getFecha());
    System.out.println("Total hechos: " + hechosList.size());
}

public List<HechosModel> getHechos() {
    System.out.println("Obteniendo lista de hechos. Total hechos: " + hechosList.size());
    return hechosList;
}

public List<HechosModel> buscarHechos(String buscar) {
    return hechosList.stream().filter(hecho -> hecho.getDescripcion().equals(buscar)).collect(Collectors.toList());
}

public boolean eliminarHecho(int numero) {
    Optional<HechosModel> optionalHecho = hechosList.stream().filter(hecho -> hecho.getNumero() == numero).findFirst();
    if (optionalHecho.isEmpty()) {
        return false;
    }
    hechosList.remove(optionalHecho.get());
    return true;
}
}
