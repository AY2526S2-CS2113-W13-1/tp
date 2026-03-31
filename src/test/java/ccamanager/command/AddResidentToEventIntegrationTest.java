package ccamanager.command;

import ccamanager.enumerations.CcaLevel;
import ccamanager.exceptions.DuplicateCcaException;
import ccamanager.exceptions.DuplicateResidentException;
import ccamanager.exceptions.InvalidCcaLevelException;
import ccamanager.exceptions.DuplicateEventException;
import ccamanager.manager.CcaManager;
import ccamanager.manager.EventManager;
import ccamanager.manager.ResidentManager;
import ccamanager.model.Cca;
import ccamanager.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration test for adding a resident to an event.
 * Ensures the managers interact correctly during command execution.
 */
class AddResidentToEventIntegrationTest {

    private ResidentManager residentManager;
    private CcaManager ccaManager;
    private EventManager eventManager;
    private Ui ui;

    @BeforeEach
    void setUp() {
        residentManager = new ResidentManager();
        ccaManager = new CcaManager();
        eventManager = new EventManager();
        ui = new Ui();
    }

    @Test
    void execute_addResidentToEvent_integrationSuccess()
            throws DuplicateResidentException, InvalidCcaLevelException,
            DuplicateCcaException, DuplicateEventException {


        residentManager.addResident("Veer", "A0302680M");
        ccaManager.addCCA("Basketball", CcaLevel.HIGH);

        Cca basketball = ccaManager.getCCAList().stream()
                .filter(c -> c.getName().equalsIgnoreCase("Basketball"))
                .findFirst()
                .orElseThrow();

        eventManager.addEvent("Finals", basketball, "2026-05-18");

        AddResidentToEventCommand command = new AddResidentToEventCommand("A0302680M", "Finals",
                "Basketball");
        command.execute(ccaManager, residentManager, eventManager, ui);

        var events = eventManager.getEventList();
        assertEquals(1, events.size(), "Should have exactly one event");

        var participants = events.get(0).getParticipants();
        assertEquals(1, participants.size(), "Event should have exactly one participant");
        assertEquals("Veer", participants.get(0).getName(), "Participant name should match");
    }
}
