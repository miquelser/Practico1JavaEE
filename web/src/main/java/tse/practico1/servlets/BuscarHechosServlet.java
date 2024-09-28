package tse.practico1.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tse.practico1.service.Interface.IHechosLocal;
import tse.practico1.models.HechosModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BuscarHechos", value = "/buscar-hechos")
public class BuscarHechosServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	@EJB
    private IHechosLocal hechosLocal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buscar = request.getParameter("buscar");

        List<HechosModel> hechos;

        // Si no se proporciona "tipo" ni "buscar", se retorna la lista completa
        if (buscar == null || buscar.isEmpty()) {
            hechos = hechosLocal.getHechos(); // Método para obtener todos los hechos
        } else {
            // Si se proporciona algún valor en "tipo" o "buscar", se hace la búsqueda específica
            hechos = hechosLocal.buscarHechos(buscar);
        }

        // Establecer los atributos para enviar a la JSP
        request.setAttribute("hechos", hechos);
        request.setAttribute("resultado", "ok");

        // Redirigir a la página JSP con los resultados
        request.getRequestDispatcher("/buscarHecho.jsp").forward(request, response);
    }
}
