package tse.practico1.models;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;

import java.io.Serializable;

import java.time.LocalDate;


@Entity
public class HechosModel implements Serializable {
   
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate fecha;
    private String descripcion;
    @Column(nullable = false)
    private Clasificacion clasificacion;
    @Column(nullable = false)
    private Estado estado;

    public HechosModel() {
        super();
    }

    public HechosModel(int numero, LocalDate fecha, String descripcion, Clasificacion clasificacion, Estado estado) {
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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