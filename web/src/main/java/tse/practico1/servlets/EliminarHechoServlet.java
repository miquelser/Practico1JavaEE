package tse.practico1.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tse.practico1.service.Interface.IHechosLocal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "EliminarHecho", value = "/eliminar-hecho")
public class EliminarHechoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(EliminarHechoServlet.class.getName());

    @EJB
    private IHechosLocal hechosLocal;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String numeroStr = request.getParameter("numero");
            int numero = Integer.parseInt(numeroStr);
            boolean eliminado = hechosLocal.eliminarHecho(numero);

            response.setContentType("text/html");
            PrintWriter out = createResponseWriter(response, eliminado, null);
            out.close();
            response.sendRedirect("listar-hechos");
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Número proporcionado no es válido: " + request.getParameter("numero"), e);
            try {
                PrintWriter out = createResponseWriter(response, false, "Error: El número proporcionado no es válido");
                out.close();
            } catch (IOException ioException) {
                logger.log(Level.SEVERE, "Error al escribir la respuesta", ioException);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar el hecho", e);
            try {
                PrintWriter out = createResponseWriter(response, false, "Error al eliminar el hecho");
                out.close();
            } catch (IOException ioException) {
                logger.log(Level.SEVERE, "Error al escribir la respuesta", ioException);
            }
            throw new RuntimeException(e);
        }
    }

    private PrintWriter createResponseWriter(HttpServletResponse response, boolean success, String errorMessage) throws IOException {
        PrintWriter out = response.getWriter();
        if (success) {
            out.println("<html><head><title>Éxito</title></head><body>");
            out.println("<h1>Hecho eliminado con éxito</h1>");
            out.println("<p>Redirigiendo a la página de lista en 5 segundos...</p>");
            out.println("</body></html>");
        } else {
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>" + (errorMessage != null ? errorMessage : "Hecho no encontrado") + "</h1>");
            out.println("<p>Redirigiendo a la página de lista en 5 segundos...</p>");
            out.println("</body></html>");
        }
        return out;
    }
}
