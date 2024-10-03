package tse.practico1.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tse.practico1.service.Interface.IHechosLocal;
import tse.practico1.models.Clasificacion;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AgregarHecho", value = "/agregar-hecho")
public class AgregarHechoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IHechosLocal hechosLocal;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descripcion = request.getParameter("descripcion");
        String fechaStr = request.getParameter("fecha");
        String calificacionStr = request.getParameter("calificacion");
        String estadoStr = request.getParameter("estado");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha;
        Clasificacion clasificacion;


        try {
            // Validar fecha
            fecha = formatter.parse(fechaStr);

            // Validar calificación
            clasificacion = Clasificacion.valueOf(calificacionStr);

            // Agregar hecho a través del servicio
            hechosLocal.agregarHecho(fecha, descripcion, clasificacion);

            // Mensaje de éxito
            request.setAttribute("mensaje", "Hecho agregado con éxito");
            request.getRequestDispatcher("agregarHecho.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            // Valor inválido para calificación
            request.setAttribute("mensajeError", "Error: Valor inválido para calificación");
            request.getRequestDispatcher("agregarHecho.jsp").forward(request, response);

        } catch (ParseException e) {
            // Formato de fecha inválido
            request.setAttribute("mensajeError", "Error: Formato de fecha inválido");
            request.getRequestDispatcher("agregarHecho.jsp").forward(request, response);

        } catch (Exception e) {
            // Error general al agregar el hecho
            request.setAttribute("mensajeError", "Error al agregar el hecho");
            request.getRequestDispatcher("agregarHecho.jsp").forward(request, response);
            throw new RuntimeException(e);
        }
    }
}