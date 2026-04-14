package ccamanager.command;

import ccamanager.enumerations.CcaLevel;
import ccamanager.manager.CcaManager;
import ccamanager.manager.ResidentManager;
import ccamanager.manager.EventManager;
import ccamanager.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCcaCommandTest {

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

    @Test
    void execute_addCca_success() {
        new AddCcaCommand("Basketball", CcaLevel.HIGH).execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(1, ccaManager.getCCAList().size());
        assertEquals("Basketball", ccaManager.getCCAList().get(0).getName());
        assertEquals(CcaLevel.HIGH, ccaManager.getCCAList().get(0).getLevel());
    }

    @Test
    void execute_addDuplicateCca_showsError() {
        new AddCcaCommand("Basketball", CcaLevel.MEDIUM).execute(ccaManager, residentManager, eventManager, ui);
        new AddCcaCommand("Basketball", CcaLevel.MEDIUM).execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(1, ccaManager.getCCAList().size());
        assertEquals("CCA Basketball already exists.", ui.getLastMessage());
    }

    @Test
    void execute_addDuplicateCcaDifferentCase_showsError() {
        new AddCcaCommand("Basketball", CcaLevel.LOW).execute(ccaManager, residentManager, eventManager, ui);
        new AddCcaCommand("basketball", CcaLevel.LOW).execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(1, ccaManager.getCCAList().size());
        assertEquals("CCA basketball already exists.", ui.getLastMessage());
    }

    @Test
    void execute_addUnknownLevel_showsError() {
        new AddCcaCommand("InvalidCCA", CcaLevel.UNKNOWN).execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(0, ccaManager.getCCAList().size());
        assertEquals("Could not add CCA: Invalid level provided.", ui.getLastMessage());
    }

    @Test
    void execute_addCca_successMessage() {
        new AddCcaCommand("Basketball", CcaLevel.HIGH).execute(ccaManager, residentManager, eventManager, ui);

        assertEquals("CCA added: Basketball (HIGH)", ui.getLastMessage());
    }

    @Test
    void execute_addCcaWithLowLevel_success() {
        new AddCcaCommand("Chess", CcaLevel.LOW).execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(1, ccaManager.getCCAList().size());
        assertEquals("Chess", ccaManager.getCCAList().get(0).getName());
        assertEquals(CcaLevel.LOW, ccaManager.getCCAList().get(0).getLevel());
        assertEquals("CCA added: Chess (LOW)", ui.getLastMessage());
    }

    @Test
    void execute_addCcaWithMediumLevel_success() {
        new AddCcaCommand("Swimming", CcaLevel.MEDIUM).execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(1, ccaManager.getCCAList().size());
        assertEquals("Swimming", ccaManager.getCCAList().get(0).getName());
        assertEquals(CcaLevel.MEDIUM, ccaManager.getCCAList().get(0).getLevel());
        assertEquals("CCA added: Swimming (MEDIUM)", ui.getLastMessage());
    }

    @Test
    void execute_addMultipleDistinctCcas_allAdded() {
        new AddCcaCommand("Basketball", CcaLevel.HIGH).execute(ccaManager, residentManager, eventManager, ui);
        new AddCcaCommand("Chess", CcaLevel.LOW).execute(ccaManager, residentManager, eventManager, ui);
        new AddCcaCommand("Swimming", CcaLevel.MEDIUM).execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(3, ccaManager.getCCAList().size());
    }

    @Test
    void execute_addDuplicateCca_listSizeUnchanged() {
        new AddCcaCommand("Basketball", CcaLevel.HIGH).execute(ccaManager, residentManager, eventManager, ui);
        new AddCcaCommand("Chess", CcaLevel.LOW).execute(ccaManager, residentManager, eventManager, ui);
        new AddCcaCommand("Basketball", CcaLevel.HIGH).execute(ccaManager, residentManager, eventManager, ui); // duplicate

        assertEquals(2, ccaManager.getCCAList().size());
    }

    @Test
    void execute_addUnknownLevel_listUnchanged() {
        new AddCcaCommand("ValidCCA", CcaLevel.HIGH).execute(ccaManager, residentManager, eventManager, ui);
        new AddCcaCommand("InvalidCCA", CcaLevel.UNKNOWN).execute(ccaManager, residentManager, eventManager, ui);

        assertEquals(1, ccaManager.getCCAList().size());
    }
}

