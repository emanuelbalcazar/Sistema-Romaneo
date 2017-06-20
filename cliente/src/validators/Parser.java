package validators;

import administrator.QueueManagement;
import administrator.Sender;
import java.util.Date;
import logger.Logger;
import logger.Status;
import message.Message;
import message.Priority;
import message.ResponseMessage;
import message.TextMessage;
import message.Type;

/**
 * Clase encargada de analizar un mensaje recibido de parte del servidor. Los
 * mensajes pueden ser confirmaciones o mensajes de textos enviados desde el
 * servidor. En el caso de un mensaje de texto debe generar un Ack de mensaje
 * recibido.
 *
 */
public class Parser {

    private Sender sender;
    
    public Parser() {
        this.sender = new Sender();
    }

    /**
     * Parsea el mensaje y realiza alguna accion dependiendo del tipo de mensaje
     * recibido.
     *
     * @param msg mensaje de respuesta del servidor.
     */
    public void parseMessage(ResponseMessage msg) {
        if (msg.getType().equals("TEXTO")) {
            generateConfirmAck(msg);
        } else if (msg.getType().equals("REENVIAR")) {
            resendMessage(msg);
        } else {
            changeStatusMessage(msg);
        }
    }

    /**
     * Genera un ack de CONFIRMADO de mensaje de texto recibido por parte del servidor.
     * 
     * @param msg de texto enviado desde el servidor para el cliente.
     * 
     */
    private void generateConfirmAck(ResponseMessage msg) {
        Message confirm = new TextMessage();

        confirm.setId(msg.getId());
        confirm.setImei(msg.getImei());
        confirm.setPriority(Priority.HIGH_PRIORITY.getPriority());
        confirm.setDescription("Mensaje de TEXTO: " + msg.getText() + " CONFIRMADO por el Cliente " + msg.getImei());
        confirm.setTimestamp(new Date());
        
        sender.sendMessage(confirm);
        Logger.getInstance().logInfo(confirm, "cliente", Status.CONFIRMED.getStatus(), "Mensaje de Texto: " + msg.getText());
    }

    private void resendMessage(ResponseMessage msg) {
        QueueManagement.getInstance().resendMessage(msg);
    }

    private void changeStatusMessage(ResponseMessage msg) {
        if (msg.getType().equals(Status.RECEIVED.getStatus())) {
            QueueManagement.getInstance().changeToReceivedStatus(msg);
        }
        else if (msg.getType().equals(Status.CONFIRMED.getStatus())) {
            QueueManagement.getInstance().changeToConfirmedStatus(msg);
        }
    }
    
}
