package tse.practico1.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tse.practico1.models.HechosModel;
import tse.practico1.service.Interface.IHechosLocal;

import java.util.List;

@Path("/hechos")
public class HechosController {

    @Inject
    private IHechosLocal hechosService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarHecho(HechosModel hecho) {
        hechosService.agregarHecho(hecho.getFecha(), hecho.getDescripcion(), hecho.getClasificacion());
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HechosModel> getHechos() {
        return hechosService.getHechos();
    }

    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HechosModel> buscarHechos(@QueryParam("descripcion") String descripcion) {
        return hechosService.buscarHechos(descripcion);
    }

    @DELETE
    @Path("/{numero}")
    public Response eliminarHecho(@PathParam("numero") int numero) {
        boolean eliminado = hechosService.eliminarHecho(numero);
        if (eliminado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}