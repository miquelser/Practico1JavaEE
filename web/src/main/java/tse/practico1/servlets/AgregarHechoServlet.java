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
import java.time.LocalDate;
import java.time.ZoneId;
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
        String clasificacionStr = request.getParameter("clasificacion");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha;



        Clasificacion clasificacion;

        try {
            // Validar fecha
            fecha = formatter.parse(fechaStr);
            LocalDate fechaLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // Validar clasificación
            clasificacion = Clasificacion.valueOf(clasificacionStr);

            // Agregar hecho a través del servicio
            hechosLocal.agregarHecho(fechaLocal, descripcion, clasificacion);

            // Mensaje de éxito
            request.setAttribute("mensaje", "Hecho agregado con éxito");
            request.getRequestDispatcher("agregarHecho.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            // Valor inválido para clasificación
            request.setAttribute("mensajeError", "Error: Valor inválido para clasificación");
            request.getRequestDispatcher("agregarHecho.jsp").forward(request, response);

        } catch (ParseException e) {
            // Formato de fecha inválido
            request.setAttribute("mensajeError", "Error: Formato de fecha inválido");
            request.getRequestDispatcher("agregarHecho.jsp").forward(request, response);

        } catch (Exception e) {
            // Error general al agregar el hecho
            request.setAttribute("mensajeError", "Error al agregar el hecho");
            request.getRequestDispatcher("agregarHecho.jsp").forward(request, response);
        }
    }
}