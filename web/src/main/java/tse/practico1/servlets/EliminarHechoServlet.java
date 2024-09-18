package tse.practico1.servlets;


import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tse.practico1.service.Interface.IHechosLocal;
import tse.practico1.service.Repository.HechoService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EliminarHecho", value = "/eliminar-hecho")
public class EliminarHechoServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;
	@EJB
	   private IHechosLocal HechoService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numeroStr = request.getParameter("numero");
        try {
            int numero = Integer.parseInt(numeroStr);
            boolean eliminado = HechoService.eliminarHecho(numero);

            response.setContentType("text/html");
            response.setHeader("Refresh", "5;url=listar-hechos");
            PrintWriter out = response.getWriter();
            if (eliminado) {
                out.println("<html><head><title>Éxito</title></head><body>");
                out.println("<h1>Hecho eliminado con éxito</h1>");
                out.println("<p>Redirigiendo a la página de lista en 5 segundos...</p>");
                out.println("</body></html>");
            } else {
                out.println("<html><head><title>Error</title></head><body>");
                out.println("<h1>Hecho no encontrado</h1>");
                out.println("<p>Redirigiendo a la página de lista en 5 segundos...</p>");
                out.println("</body></html>");
            }
            out.close();
        } catch (Exception e) {
            response.setContentType("text/html");
            response.setHeader("Refresh", "5;url=listar-hechos");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Error al eliminar el hecho</h1>");
            out.println("<p>Redirigiendo a la página de lista en 5 segundos...</p>");
            out.println("</body></html>");
            out.close();
            throw new RuntimeException(e);
        }
    }
}
