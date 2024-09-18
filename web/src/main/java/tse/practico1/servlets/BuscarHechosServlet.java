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
    private IHechosLocal HechoService;
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        String buscar = request.getParameter("buscar");
        List<HechosModel> hechos = HechoService.buscarHechos(tipo, buscar);
        request.setAttribute("hechos", hechos);
        request.setAttribute("resultado", "ok");

        request.getRequestDispatcher("/buscar.jsp").forward(request, response);
    }
}
