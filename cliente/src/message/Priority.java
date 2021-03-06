package message;

/**
 * Enumerado de prioridades de mensajes.
 * 
 */
public enum Priority {
    
    MAX_PRIORITY(4),
    HIGH_PRIORITY(3),
    MEDIUM_PRIORITY(2),
    LOW_PRIORITY(1);

    private final int priority;

    Priority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
