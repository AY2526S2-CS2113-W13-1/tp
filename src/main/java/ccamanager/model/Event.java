package ccamanager.model;

public class Event {

    private final String eventName;
    private final Cca cca;
    private final String eventDate;

    public Event(String eventName, Cca cca, String eventDate) {
        this.eventName = eventName;
        this.cca = cca;
        this.eventDate = eventDate;

    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public Cca getCca() {
        return cca;
    }

    @Override
    public String toString() {
        return "Event :" + eventName + " of the Cca " + cca + ". Held on " + eventDate + ".";
    }
}
