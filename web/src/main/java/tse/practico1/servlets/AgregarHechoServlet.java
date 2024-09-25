package tse.practico1.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tse.practico1.service.Interface.IHechosLocal;
import tse.practico1.models.Calificacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AgregarHecho", value = "/agregar-hecho")
public class AgregarHechoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IHechosLocal HechoService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descripcion = request.getParameter("descripcion");
        String fechaStr = request.getParameter("fecha");
        String calificacionStr = request.getParameter("calificacion");
        String estadoStr = request.getParameter("estado");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha;
        Calificacion calificacion;

        try {
           
            fecha = formatter.parse(fechaStr);
            
            calificacion = Calificacion.valueOf(calificacionStr);
          
            HechoService.agregarHecho(fecha, descripcion, calificacion);

            response.setContentType("text/html");
            response.sendRedirect("agregarHecho.jsp");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Éxito</title></head><body>");
            out.println("<h1>Hecho agregado con éxito</h1>");
            out.println("<p>Redirigiendo a la página de agregar en 2 segundos...</p>");
            out.println("</body></html>");
            out.close();
        } catch (IllegalArgumentException e) {            
            response.setContentType("text/html");
            response.sendRedirect("agregarHecho.jsp");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Error: Valor inválido para calificación</h1>");
            out.println("<p>Redirigiendo a la página de agregar en 2 segundos...</p>");
            out.println("</body></html>");
            out.close();
        } catch (ParseException e) {            
            response.setContentType("text/html");
            response.sendRedirect("agregarHecho.jsp");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Error: Formato de fecha inválido</h1>");
            out.println("<p>Redirigiendo a la página de agregar en 2 segundos...</p>");
            out.println("</body></html>");
            out.close();
        } catch (Exception e) {            
            response.setContentType("text/html");
            response.sendRedirect("agregarHecho.jsp");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Error al agregar el hecho</h1>");
            out.println("<p>Redirigiendo a la página de agregar en 2 segundos...</p>");
            out.println("</body></html>");
            out.close();
            throw new RuntimeException(e);
        }
    }
}