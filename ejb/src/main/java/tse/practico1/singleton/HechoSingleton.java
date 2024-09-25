package tse.practico1.singleton;

import jakarta.ejb.Singleton;
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
public class HechoSingleton{
    private List<HechosModel> hechosList = new ArrayList<>();
    private AtomicInteger nextNumero = new AtomicInteger(1); 

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

    public boolean eliminarHecho(int numero) {
        Optional<HechosModel> optionalHecho = hechosList.stream().filter(hecho -> hecho.getNumero() == numero).findFirst();
        if (optionalHecho.isEmpty()) {
            return false;
        }
        hechosList.remove(optionalHecho.get());
        return true;
    }
}
