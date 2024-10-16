package tse.practico1.singleton;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import tse.practico1.models.Clasificacion;
import tse.practico1.models.Estado;
import tse.practico1.models.HechosModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Singleton
@Startup
@Lock(LockType.READ)
public class HechoSingleton {


    @PersistenceContext(unitName = "miUnidadDePersistencia")
    private EntityManager em;
    private AtomicInteger nextNumero = new AtomicInteger(1);

    @Lock(LockType.WRITE)
    @Transactional
    public void agregarHecho(LocalDate fecha, String descripcion, Clasificacion clasificacion) {
        int numero = nextNumero.getAndIncrement();
        HechosModel hecho = new HechosModel(numero, fecha, descripcion, clasificacion, Estado.NUEVO);
        em.merge(hecho);
        System.out.println("Hecho agregado a la base de datos: " + hecho.getDescripcion() + " en la fecha " + hecho.getFecha());
    }

    public List<HechosModel> getHechos() {
        System.out.println("Obteniendo lista de hechos desde la base de datos.");
        return em.createQuery("SELECT h FROM HechosModel h", HechosModel.class).getResultList();
    }

    public List<HechosModel> buscarHechos(String buscar) {
        System.out.println("Buscando hechos que coincidan con: " + buscar);
        return em.createQuery("SELECT h FROM HechosModel h WHERE LOWER(h.descripcion) LIKE :buscar", HechosModel.class)
                .setParameter("buscar", "%" + buscar.toLowerCase() + "%")
                .getResultList();
    }

    @Lock(LockType.WRITE)
    @Transactional
    public boolean eliminarHecho(int numero) {
        Optional<HechosModel> optionalHecho = em.createQuery("SELECT h FROM HechosModel h WHERE h.numero = :numero", HechosModel.class)
                .setParameter("numero", numero)
                .getResultList()
                .stream().findFirst();

        if (optionalHecho.isEmpty()) {
            return false;
        }
        em.remove(optionalHecho.get());
        System.out.println("Hecho eliminado: " + optionalHecho.get().getDescripcion());
        return true;
    }
}
/*
@Singleton
@Startup
@Lock(LockType.READ)
public class HechoSingleton{
    private List<HechosModel> hechosList = new ArrayList<>();
    private AtomicInteger nextNumero = new AtomicInteger(1);

    @Lock(LockType.WRITE)
    public void agregarHecho(LocalDate fecha, String descripcion, Clasificacion clasificacion) {
        int numero = nextNumero.getAndIncrement();
        HechosModel hecho = new HechosModel(numero, fecha, descripcion, clasificacion, Estado.NUEVO);
        hechosList.add(hecho);
        System.out.println("Hecho agregado: " + hecho.getDescripcion() + " en la fecha " + hecho.getFecha());
        System.out.println("Total hechos: " + hechosList.size());
    }

    public List<HechosModel> getHechos() {
        System.out.println("Obteniendo lista de hechos. Total hechos: " + hechosList.size());
        return hechosList;
    }

    public List<HechosModel> buscarHechos(String buscar) {
        return hechosList.stream()
                .filter(hecho -> hecho.getDescripcion().toLowerCase().contains(buscar.toLowerCase()))
                .collect(Collectors.toList());
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
*/