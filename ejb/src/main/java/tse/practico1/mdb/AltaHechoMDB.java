package tse.practico1.mdb;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import jakarta.inject.Inject;
import tse.practico1.models.Clasificacion;
import tse.practico1.service.Interface.IHechosRemote;
import java.util.Date;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/queue_alta_hechos"), // Cambiado al nuevo nombre de la cola
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
        }
)
public class AltaHechoMDB implements MessageListener { // using ActiveMQ Artemis

    @Inject
    private IHechosRemote hechosBean;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String texto = ((TextMessage) message).getText();
                String[] atributos = texto.split("\\|");

                Date fecha = new Date();
                String descripcion = atributos[0];
                Clasificacion clasificacion = Clasificacion.valueOf(atributos[1]);

                hechosBean.agregarHecho(fecha, descripcion, clasificacion);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
