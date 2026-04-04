package ccamanager.command;

import ccamanager.exceptions.EventNotFoundException;
import ccamanager.manager.EventManager;
import ccamanager.model.Cca;
import ccamanager.enumerations.CcaLevel;
import ccamanager.model.Resident;
import ccamanager.model.Event;
import ccamanager.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ViewMyEventsTest {
    private EventManager eventManager;
    private Resident resident1;
    private Resident resident2;
    private Cca cca;
    private Ui ui;

    @BeforeEach
    void setup(){
        eventManager = new EventManager();
        resident1 = new Resident("John Doe", "A1234567X");
        resident2 = new Resident("Jane Tan", "A7654321Y");
        cca = new Cca("Basketball",CcaLevel.HIGH);
        ui = new Ui();

    }

    @Test
    public void viewMyEvents_noEvents() {
        ArrayList<Event> result = eventManager.viewMyEvents("A1234567X");
        assertEquals(0, result.size());
    }

    @Test
    public void viewMyEvents_oneMatchingEvent() throws EventNotFoundException {
        eventManager.addEvent("Training",cca,"2026-04-03");
        eventManager.addResidentToEvent("Training",cca,resident1);
        ArrayList<Event> result = eventManager.viewMyEvents("A1234567X");
        assertEquals(1, result.size());
        assertEquals("Training", result.get(0).getEventName());
    }
    @Test
    public void viewMyEvents_multipleMatchingEvent() throws EventNotFoundException {
        eventManager.addEvent("Training",cca,"2026-04-03");
        eventManager.addEvent("Game",cca,"2026-04-04");
        eventManager.addEvent("Team Bonding",cca,"2026-04-04");
        eventManager.addResidentToEvent("Training",cca,resident1);
        eventManager.addResidentToEvent("Game",cca,resident1);
        eventManager.addResidentToEvent("Team Bonding",cca,resident1);
        ArrayList<Event> result = eventManager.viewMyEvents("A1234567X");
        assertEquals(3, result.size());
        assertEquals("Training", result.get(0).getEventName());
        assertEquals("Game", result.get(1).getEventName());
        assertEquals("Team Bonding", result.get(2).getEventName());
    }
}
