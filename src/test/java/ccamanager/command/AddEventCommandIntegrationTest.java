package ccamanager.command;

import ccamanager.enumerations.CcaLevel;
import ccamanager.exceptions.DuplicateCcaException;
import ccamanager.exceptions.InvalidCcaLevelException;
import ccamanager.manager.CcaManager;
import ccamanager.manager.EventManager;
import ccamanager.manager.ResidentManager;
import ccamanager.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddEventIntegrationTest {

    private CcaManager ccaManager;
    private ResidentManager residentManager;
    private EventManager eventManager;
    private Ui ui;

    @BeforeEach
    void setUp() {
        ccaManager = new CcaManager();
        residentManager = new ResidentManager();
        eventManager = new EventManager();
        ui = new Ui();
    }

    /**
     * Test for adding a new event
     * @throws DuplicateCcaException To handle duplicate CCAs created
     * @throws InvalidCcaLevelException To handle Invalid CCAs
     */
    @Test
    void execute_addEvent_success() throws DuplicateCcaException, InvalidCcaLevelException {
        ccaManager.addCCA("Dance", CcaLevel.LOW);

        AddEventCommand command = new AddEventCommand("Annual Concert", "Dance",
                "2026-12-25");
        command.execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(1, eventManager.getEventList().size());
        assertEquals("Annual Concert", eventManager.getEventList().get(0).getEventName());
        assertEquals("Event added: Annual Concert for the CCA Dance, during 2026-12-25", ui.getLastMessage());
    }
    /**
     * Test for adding a new event with an invalid CCA
     */
    @Test
    void execute_addEvent_ccaNotFound() {

        AddEventCommand command = new AddEventCommand("Match Day", "Football",
                "2026-06-01");
        command.execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(0, eventManager.getEventList().size());
        assertEquals("Football not found.", ui.getLastMessage());
    }

    /**
     * Tests for duplicate events
     * @throws DuplicateCcaException  To handle duplicate CCAs created
     * @throws InvalidCcaLevelException To handle Invalid CCAs
     */
    @Test
    void execute_addDuplicateEvent_showsError() throws DuplicateCcaException, InvalidCcaLevelException {
        ccaManager.addCCA("CodingClub", CcaLevel.MEDIUM);
        AddEventCommand firstCommand = new AddEventCommand("Hackathon", "CodingClub",
                "2026-09-10");
        firstCommand.execute(ccaManager, residentManager, eventManager, ui);

        AddEventCommand duplicateCommand = new AddEventCommand("Hackathon", "CodingClub",
                "2026-09-10");
        duplicateCommand.execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(1, eventManager.getEventList().size());
        assertEquals("Event 'Hackathon' already exists for this CCA on this date.", ui.getLastMessage());
    }

    @Test
    void execute_addEvent_invalidDateFormat_showsError() throws DuplicateCcaException, InvalidCcaLevelException {
        ccaManager.addCCA("Dance", CcaLevel.LOW);

        AddEventCommand command = new AddEventCommand("Annual Concert", "Dance", "25-12-2026");
        command.execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(0, eventManager.getEventList().size());
        assertEquals("Please enter a valid date in YYYY-MM-DD format.", ui.getLastMessage());
    }

    @Test
    void execute_addEvent_invalidDateFormat_listUnchanged() throws DuplicateCcaException, InvalidCcaLevelException {
        ccaManager.addCCA("Dance", CcaLevel.LOW);
        AddEventCommand validCommand = new AddEventCommand("Annual Concert", "Dance", "2026-12-25");
        validCommand.execute(ccaManager, residentManager, eventManager, ui);

        AddEventCommand invalidCommand = new AddEventCommand("Gala Night", "Dance", "not-a-date");
        invalidCommand.execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(1, eventManager.getEventList().size());
    }

    @Test
    void execute_addEvent_ccaNameCaseInsensitive_success() throws DuplicateCcaException, InvalidCcaLevelException {
        ccaManager.addCCA("Dance", CcaLevel.LOW);

        AddEventCommand command = new AddEventCommand("Annual Concert", "dance", "2026-12-25");
        command.execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(1, eventManager.getEventList().size());
        assertEquals("Annual Concert", eventManager.getEventList().get(0).getEventName());
    }

    @Test
    void execute_addSameEventNameDifferentDate_success() throws DuplicateCcaException, InvalidCcaLevelException {
        ccaManager.addCCA("CodingClub", CcaLevel.MEDIUM);

        new AddEventCommand("Hackathon", "CodingClub", "2026-09-10")
                .execute(ccaManager, residentManager, eventManager, ui);
        new AddEventCommand("Hackathon", "CodingClub", "2026-10-10")
                .execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(2, eventManager.getEventList().size());
    }

    @Test
    void execute_addSameEventNameDifferentCca_success() throws DuplicateCcaException, InvalidCcaLevelException {
        ccaManager.addCCA("CodingClub", CcaLevel.MEDIUM);
        ccaManager.addCCA("RoboticsClub", CcaLevel.HIGH);

        new AddEventCommand("Hackathon", "CodingClub", "2026-09-10")
                .execute(ccaManager, residentManager, eventManager, ui);
        new AddEventCommand("Hackathon", "RoboticsClub", "2026-09-10")
                .execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(2, eventManager.getEventList().size());
        assertEquals("Event added: Hackathon for the CCA RoboticsClub, during 2026-09-10", ui.getLastMessage());
    }

    @Test
    void execute_addMultipleEventsToSameCca_success() throws DuplicateCcaException, InvalidCcaLevelException {
        ccaManager.addCCA("Dance", CcaLevel.LOW);

        new AddEventCommand("Annual Concert", "Dance", "2026-12-25")
                .execute(ccaManager, residentManager, eventManager, ui);
        new AddEventCommand("Mid-Year Show", "Dance", "2026-06-15")
                .execute(ccaManager, residentManager, eventManager, ui);
        new AddEventCommand("Welcome Night", "Dance", "2026-01-10")
                .execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(3, eventManager.getEventList().size());
    }

    @Test
    void execute_addEvent_ccaNotFound_listUnchanged() throws DuplicateCcaException, InvalidCcaLevelException {
        ccaManager.addCCA("Dance", CcaLevel.LOW);
        new AddEventCommand("Annual Concert", "Dance", "2026-12-25")
                .execute(ccaManager, residentManager, eventManager, ui);

        new AddEventCommand("Match Day", "Football", "2026-06-01")
                .execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(1, eventManager.getEventList().size());
    }
}
