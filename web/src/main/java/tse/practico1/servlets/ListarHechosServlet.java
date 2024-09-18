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

@WebServlet(name = "ListarHechos", value = "/listar-hechos")
public class ListarHechosServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;
	@EJB
	   private IHechosLocal HechoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HechosModel> hechos = HechoService.getHechos();
        request.setAttribute("hechos", hechos);
        request.getRequestDispatcher("/listar.jsp").forward(request, response);
    }
}
