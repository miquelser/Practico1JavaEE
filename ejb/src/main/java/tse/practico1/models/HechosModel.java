package tse.practico1.models;

import java.io.Serializable;
import java.util.Date;

public class HechosModel implements Serializable {
   
	private static final long serialVersionUID = 1L;
	private int numero;
    private Date fecha;
    private String descripcion;
    private Clasificacion clasificacion;
    private Estado estado;

    public HechosModel() {
        super();
    }

    public HechosModel(int numero, Date fecha, String descripcion, Clasificacion clasificacion, Estado estado) {
        super();
        this.numero = numero;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.clasificacion = clasificacion;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}