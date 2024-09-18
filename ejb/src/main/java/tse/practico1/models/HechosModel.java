package tse.practico1.models;

import java.io.Serializable;
import java.util.Date;

public class HechosModel implements Serializable {
   
	private static final long serialVersionUID = 1L;
	private int numero;
    private Date fecha;
    private String descripcion;
    private Calificacion calificacion;
    private Estado estado;

    public HechosModel() {
        super();
    }

    public HechosModel(int numero, Date fecha, String descripcion, Calificacion calificacion, Estado estado) {
        super();
        this.numero = numero;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
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

    public Calificacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Calificacion calificacion) {
        this.calificacion = calificacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}