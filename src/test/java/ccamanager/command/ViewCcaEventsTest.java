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

public class ViewCcaEventsTest {
    private EventManager eventManager;
    private Resident resident1;
    private Resident resident2;
    private Cca cca1;
    private Cca cca2;
    private Ui ui;

    @BeforeEach
    void setup(){
        eventManager = new EventManager();
        resident1 = new Resident("John Doe", "A1234567X");
        resident2 = new Resident("Jane Tan", "A7654321Y");
        cca1 = new Cca("Basketball",CcaLevel.HIGH);
        cca2 = new Cca("Football",CcaLevel.HIGH);
        ui = new Ui();
    }

    @Test
    public void viewCcaEvents_noEvents() {
        ArrayList<Event> result = eventManager.viewCcaEvents("Football");
        assertEquals(0, result.size());
    }

    @Test
    public void viewCcaEvents_oneMatchingEvent() throws EventNotFoundException {
        eventManager.addEvent("Training",cca2,"2026-04-03");
        ArrayList<Event> result = eventManager.viewCcaEvents("Football");
        assertEquals(1, result.size());
        assertEquals("Training", result.get(0).getEventName());
    }
    @Test
    public void viewCcaEvents_multipleMatchingEvent() throws EventNotFoundException {
        eventManager.addEvent("Training",cca1,"2026-04-03");
        eventManager.addEvent("Game",cca1,"2026-04-04");
        eventManager.addEvent("Team Bonding",cca1,"2026-04-04");
        ArrayList<Event> result = eventManager.viewCcaEvents("Basketball");
        assertEquals(3, result.size());
        assertEquals("Training", result.get(0).getEventName());
        assertEquals("Game", result.get(1).getEventName());
        assertEquals("Team Bonding", result.get(2).getEventName());
    }

    @Test
    public void viewCcaEvents_mixedCcas_onlyMatchingReturned() throws EventNotFoundException {
        eventManager.addEvent("Training", cca1, "2026-04-03");
        eventManager.addEvent("Game", cca2, "2026-04-04");
        eventManager.addEvent("Scrimmage", cca1, "2026-04-05");

        ArrayList<Event> result = eventManager.viewCcaEvents("Basketball");

        assertEquals(2, result.size());
        assertEquals("Training", result.get(0).getEventName());
        assertEquals("Scrimmage", result.get(1).getEventName());
    }

    @Test
    public void viewCcaEvents_mixedCcas_otherCcaUnaffected() throws EventNotFoundException {
        eventManager.addEvent("Training", cca1, "2026-04-03");
        eventManager.addEvent("Game", cca2, "2026-04-04");
        eventManager.addEvent("Scrimmage", cca1, "2026-04-05");

        ArrayList<Event> result = eventManager.viewCcaEvents("Football");

        assertEquals(1, result.size());
        assertEquals("Game", result.get(0).getEventName());
    }

    @Test
    public void viewCcaEvents_caseInsensitiveCcaName_returnsMatchingEvents() throws EventNotFoundException {
        eventManager.addEvent("Training", cca1, "2026-04-03");

        ArrayList<Event> result = eventManager.viewCcaEvents("basketball");

        assertEquals(1, result.size());
        assertEquals("Training", result.get(0).getEventName());
    }

    @Test
    public void viewCcaEvents_caseInsensitiveCcaNameUpperCase_returnsMatchingEvents() throws EventNotFoundException {
        eventManager.addEvent("Training", cca1, "2026-04-03");

        ArrayList<Event> result = eventManager.viewCcaEvents("BASKETBALL");

        assertEquals(1, result.size());
        assertEquals("Training", result.get(0).getEventName());
    }

    @Test
    public void viewCcaEvents_nonExistentCcaName_returnsEmpty() throws EventNotFoundException {
        eventManager.addEvent("Training", cca1, "2026-04-03");
        eventManager.addEvent("Game", cca2, "2026-04-04");

        ArrayList<Event> result = eventManager.viewCcaEvents("Swimming");

        assertEquals(0, result.size());
    }

    @Test
    public void viewCcaEvents_correctDatePreserved() throws EventNotFoundException {
        eventManager.addEvent("Training", cca1, "2026-04-03");

        ArrayList<Event> result = eventManager.viewCcaEvents("Basketball");

        assertEquals(1, result.size());
        assertEquals("2026-04-03", result.get(0).getEventDate());
    }

    @Test
    public void viewCcaEvents_multipleEventsCorrectDatesPreserved() throws EventNotFoundException {
        eventManager.addEvent("Training", cca1, "2026-04-03");
        eventManager.addEvent("Game", cca1, "2026-05-10");

        ArrayList<Event> result = eventManager.viewCcaEvents("Basketball");

        assertEquals(2, result.size());
        assertEquals("2026-04-03", result.get(0).getEventDate());
        assertEquals("2026-05-10", result.get(1).getEventDate());
    }

    @Test
    public void viewCcaEvents_sameEventNameDifferentCcas_onlyCorrectCcaReturned() throws EventNotFoundException {
        eventManager.addEvent("Training", cca1, "2026-04-03");
        eventManager.addEvent("Training", cca2, "2026-04-03");

        ArrayList<Event> result = eventManager.viewCcaEvents("Basketball");

        assertEquals(1, result.size());
        assertEquals("Training", result.get(0).getEventName());
    }

    @Test
    public void viewCcaEvents_oneMatchAmongMany_preciseFilter() throws EventNotFoundException {
        eventManager.addEvent("Event1", cca2, "2026-04-01");
        eventManager.addEvent("Event2", cca2, "2026-04-02");
        eventManager.addEvent("Event3", cca1, "2026-04-03");
        eventManager.addEvent("Event4", cca2, "2026-04-04");

        ArrayList<Event> result = eventManager.viewCcaEvents("Basketball");

        assertEquals(1, result.size());
        assertEquals("Event3", result.get(0).getEventName());
    }
}
