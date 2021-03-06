package logger;

import java.util.Date;
import message.Message;
import message.ResponseMessage;

/**
 * Clase encargada de generar el mensaje de log correspondiente y enviarlo a
 * encolar a rabbitmq.
 *
 */
public class Logger {

    LoggerSender sender;

    private Logger() {
        sender = LoggerSender.getInstance();
    }

    public void logInfo(Message msg, String source, String status, String description) {
        createLogMessage(msg, source, "info", status, description);
    }

    public void logError(Message msg, String source, String status, String description) {
        createLogMessage(msg, source, "error", status, description);
    }

    public void logTrace(Message msg, String source, String status, String description) {
        createLogMessage(msg, source, "trace", status, description);
    }

    public void logWarning(Message msg, String source, String status, String description) {
        createLogMessage(msg, source, "warning", status, description);
    }

    public void logDebug(Message msg, String source, String status, String description) {
        createLogMessage(msg, source, "debug", status, description);
    }

    private void createLogMessage(Message msg, String source, String level, String status, String description) {

        LogMessage log = new LogMessage();

        log.setMessageId(msg.getId());
        log.setImei(msg.getImei());
        log.setMessageType(msg.getType());
        log.setMessageSubType(getMessageSubType(msg));
        log.setLevel(level);
        log.setStatus(status);
        log.setSource(source);
        log.setDescription(description);
        log.setTimestamp(new Date());

        sendLog(log);
    }

    private void sendLog(LogMessage log) {
        sender.sendLog(log);
    }

    private String getMessageSubType(Message msg) {
        return (msg.getSubType().length() > 0) ? msg.getSubType() : "";
    }

    /**
     * Utilizado para la implementacion del patron de diseño Singleton.
     *
     * @return una unica instancia de la clase.
     */
    public static Logger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    private static class LoggerHolder {

        private static final Logger INSTANCE = new Logger();
    }

}
