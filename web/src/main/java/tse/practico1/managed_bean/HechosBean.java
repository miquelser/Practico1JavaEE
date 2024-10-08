package tse.practico1.managed_bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import tse.practico1.models.Clasificacion;
import tse.practico1.service.Interface.IHechosLocal;
import tse.practico1.models.HechosModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Named
@SessionScoped
public class HechosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private IHechosLocal HechoService;

    private List<HechosModel> hechosList;
    private HechosModel selectedHecho;
    private Date fecha;
    private String descripcion;
    private Clasificacion clasificacion;
    private String tipoBusqueda;
    private String valorBusqueda;

    @PostConstruct
    public void init() {
        this.hechosList = HechoService.getHechos();
    }

    public void agregarHecho() {
        try {
            HechoService.agregarHecho(fecha, descripcion, clasificacion);
            this.hechosList = HechoService.getHechos();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void eliminarHecho(int numero) {
        if (HechoService.eliminarHecho(numero)) {
            this.hechosList = HechoService.getHechos();
        }
    }

    public void buscarHechos() {
        this.hechosList = HechoService.buscarHechos(valorBusqueda);
    }

    public String formatFecha(Date fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(fecha);
    }

    public List<HechosModel> getHechosList() {
        return hechosList;
    }

    public void setHechosList(List<HechosModel> hechosList) {
        this.hechosList = hechosList;
    }

    public HechosModel getSelectedHecho() {
        return selectedHecho;
    }

    public void setSelectedHecho(HechosModel selectedHecho) {
        this.selectedHecho = selectedHecho;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Cambiar el nombre de los métodos aquí
    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(String tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public String getValorBusqueda() {
        return valorBusqueda;
    }

    public void setValorBusqueda(String valorBusqueda) {
        this.valorBusqueda = valorBusqueda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HechosBean that = (HechosBean) o;
        return Objects.equals(fecha, that.fecha) &&
                Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(clasificacion, that.clasificacion) &&
                Objects.equals(tipoBusqueda, that.tipoBusqueda) &&
                Objects.equals(valorBusqueda, that.valorBusqueda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, descripcion, clasificacion, tipoBusqueda, valorBusqueda);
    }

    @Override
    public String toString() {
        return "HechosBean{" +
                "fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", clasificacion=" + clasificacion +
                ", tipoBusqueda='" + tipoBusqueda + '\'' +
                ", valorBusqueda='" + valorBusqueda + '\'' +
                '}';
    }
}
