package tse.practico1.data;
import tse.practico1.models.HechosModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class HechosDao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    public void guardar(HechosDao hecho) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(hecho);
        em.getTransaction().commit();
        em.close();
    }
    public List<HechosModel> listar() {
        EntityManager em = emf.createEntityManager();
        List<HechosModel> hechos = em.createQuery("SELECT h FROM HechosModel h", HechosModel.class).getResultList();
        em.close();
        return hechos;
    }


}
