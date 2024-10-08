package tse.practico1.servlets;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tse.practico1.models.Clasificacion;
import tse.practico1.service.Interface.IHechosLocal;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "EnviarMensaje", value = "/enviar-mensaje")
public class EnviarMensajeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(mappedName = "java:/jms/queue/queue_alta_hechos")
    private Queue queue;

    @Inject
    private JMSContext context;

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
            // Validar clasificación
            clasificacion = Clasificacion.valueOf(clasificacionStr);

            // Crear mensaje
            String mensaje = descripcion + "|" + fechaStr + "|" + clasificacionStr;

            // Enviar el mensaje a la cola
            context.createProducer().send(queue, mensaje);

            // Mensaje de éxito
            request.setAttribute("mensaje", "Mensaje enviado con éxito");
            request.getRequestDispatcher("enviar.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            // Valor inválido para clasificación
            request.setAttribute("mensajeError", "Error: Valor inválido para clasificación");
            request.getRequestDispatcher("enviar.jsp").forward(request, response);

        } catch (ParseException e) {
            // Formato de fecha inválido
            request.setAttribute("mensajeError", "Error: Formato de fecha inválido");
            request.getRequestDispatcher("enviar.jsp").forward(request, response);

        } catch (Exception e) {
            // Error general al enviar el mensaje
            request.setAttribute("mensajeError", "Error al enviar el mensaje");
            request.getRequestDispatcher("enviar.jsp").forward(request, response);
            throw new RuntimeException(e);
        }
    }
}