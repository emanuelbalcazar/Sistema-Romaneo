package message;

/**
 * Representa un mensaje de texto comun.
 *
 */
public class TextMessage extends Message{

    private String text;

    public TextMessage() {

    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
