package ccamanager.manager;

import ccamanager.model.Cca;
import ccamanager.exceptions.DuplicateEventException;
import ccamanager.enumerations.CcaLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventManagerTest {
    private EventManager eventManager;
    private Cca testCca;

    @BeforeEach
    void setUp() {
        eventManager = new EventManager();
        testCca = new Cca("Basketball", CcaLevel.HIGH);
    }

    @Test
    void addEvent_success() throws DuplicateEventException {
        eventManager.addEvent("Training", testCca, "2026-04-01");
        assertEquals(1, eventManager.getEventList().size());
    }

    @Test
    void addEvent_duplicate_throwsException() throws DuplicateEventException {
        eventManager.addEvent("Training", testCca, "2026-04-01");

        assertThrows(DuplicateEventException.class, () -> {
            eventManager.addEvent("Training", testCca, "2026-04-01");
        });
    }
}


