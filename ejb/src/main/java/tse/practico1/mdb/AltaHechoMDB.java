package tse.practico1.mdb;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import tse.practico1.models.Clasificacion;
import tse.practico1.service.Interface.IHechosRemote;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/queue_alta_hechos"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
        }
)
public class AltaHechoMDB implements MessageListener { // using ActiveMQ Artemis

    @EJB
    private IHechosRemote hechosBean;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String texto = ((TextMessage) message).getText();
                String[] atributos = texto.split("\\|");

                String descripcion = atributos[0];


                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = formatter.parse(atributos[1]);
                LocalDate fechaLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                Clasificacion clasificacion = Clasificacion.valueOf(atributos[2]);

                hechosBean.agregarHecho(fechaLocal, descripcion, clasificacion);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
