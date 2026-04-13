package ccamanager.command;

import ccamanager.enumerations.CcaLevel;
import ccamanager.manager.CcaManager;
import ccamanager.manager.EventManager;
import ccamanager.manager.ResidentManager;
import ccamanager.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewMyEventsCommandTest {

    private CcaManager ccaManager;
    private ResidentManager residentManager;
    private EventManager eventManager;
    private Ui ui;

    @BeforeEach
    void setUp() {
        // Initialize fresh managers and UI for every test
        ccaManager = new CcaManager();
        residentManager = new ResidentManager();
        eventManager = new EventManager();
        ui = new Ui();
    }

    @Test
    void execute_viewMyEvents_residentNotFound() {
        // Attempt to view events for a resident that hasn't been added
        assertDoesNotThrow(() -> new ViewMyEventsCommand("A1234567X")
                .execute(ccaManager, residentManager, eventManager, ui));

        // Note: Update this string to match exactly what your ResidentNotFoundException throws
        assertEquals("A1234567X not found.", ui.getLastMessage());
    }

    @Test
    void execute_viewMyEvents_noEvents() {
        // Setup: Add resident, but don't add them to any events
        new AddResidentCommand("John Doe", "A1234567X")
                .execute(ccaManager, residentManager, eventManager, ui);

        assertDoesNotThrow(() -> new ViewMyEventsCommand("A1234567X")
                .execute(ccaManager, residentManager, eventManager, ui));

        // Note: Update this string to match exactly what your Ui class prints when the list is empty
        assertEquals("There are currently no events registered for A1234567X.", ui.getLastMessage());
    }

    @Test
    void execute_viewMyEvents_oneMatchingEvent() {
        // Setup: Build the environment
        new AddCcaCommand("Basketball", CcaLevel.HIGH).execute(ccaManager, residentManager, eventManager, ui);
        new AddResidentCommand("John Doe", "A1234567X")
                .execute(ccaManager, residentManager, eventManager, ui);
        new AddEventCommand("Training", "Basketball", "2026-04-03")
                .execute(ccaManager, residentManager, eventManager, ui);

        // Setup: Link the resident to the event
        new AddResidentToEventCommand("A1234567X", "Training", "Basketball")
                .execute(ccaManager, residentManager, eventManager, ui);

        // Execute target command
        assertDoesNotThrow(() -> new ViewMyEventsCommand("A1234567X")
                .execute(ccaManager, residentManager, eventManager, ui));

        // Note: Adjust the expected string to match exactly how your Ui.java formats the list
        String expectedOutput = "Here are the events for John Doe (A1234567X):\n" +
                "1. Event :Training of the Cca Basketball. Held on 2026-04-03.";
        assertEquals(expectedOutput, ui.getLastMessage());
    }

    @Test
    void execute_viewMyEvents_multipleMatchingEvents() {
        // Setup: Build the environment
        new AddCcaCommand("Basketball", CcaLevel.HIGH).execute(ccaManager, residentManager, eventManager, ui);
        new AddResidentCommand("John Doe", "A1234567X")
                .execute(ccaManager, residentManager, eventManager, ui);

        // Add two events
        new AddEventCommand("Training", "Basketball", "2026-04-03")
                .execute(ccaManager, residentManager, eventManager, ui);
        new AddEventCommand("Game", "Basketball", "2026-04-04")
                .execute(ccaManager, residentManager, eventManager, ui);

        // Link the resident to both events
        new AddResidentToEventCommand("A1234567X", "Training", "Basketball")
                .execute(ccaManager, residentManager, eventManager, ui);
        new AddResidentToEventCommand("A1234567X", "Game", "Basketball")
                .execute(ccaManager, residentManager, eventManager, ui);

        // Execute target command
        assertDoesNotThrow(() -> new ViewMyEventsCommand("A1234567X")
                .execute(ccaManager, residentManager, eventManager, ui));

        // Note: Adjust the expected string to match exactly how your Ui.java formats the list
        String expectedOutput = "Here are the events for John Doe (A1234567X):\n" +
                "1. Event :Training of the Cca Basketball. Held on 2026-04-03.\n" +
                "2. Event :Game of the Cca Basketball. Held on 2026-04-04.";
        assertEquals(expectedOutput, ui.getLastMessage());
    }
}
