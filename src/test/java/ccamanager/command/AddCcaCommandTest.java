package ccamanager.command;

import ccamanager.manager.CcaManager;
import ccamanager.manager.ResidentManager;
import ccamanager.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCcaCommandTest {

    private CcaManager ccaManager;
    private ResidentManager residentManager;
    private Ui ui;

    @BeforeEach
    void setUp() {
        ccaManager = new CcaManager();
        residentManager = new ResidentManager();
        ui = new Ui();
    }

    @Test
    void execute_addCca_success() {
        new AddCcaCommand("Basketball").execute(ccaManager, residentManager, ui);
        assertEquals(1, ccaManager.getCCAList().size());
        assertEquals("Basketball", ccaManager.getCCAList().get(0).getName());
    }

    @Test
    void execute_addDuplicateCca_showsError() {
        new AddCcaCommand("Basketball").execute(ccaManager, residentManager, ui);
        new AddCcaCommand("Basketball").execute(ccaManager, residentManager, ui);
        assertEquals(1, ccaManager.getCCAList().size());
        assertEquals("CCA Basketball already exists.", ui.getLastMessage());
    }

    @Test
    void execute_addDuplicateCcaDifferentCase_showsError() {
        new AddCcaCommand("Basketball").execute(ccaManager, residentManager, ui);
        new AddCcaCommand("basketball").execute(ccaManager, residentManager, ui);
        assertEquals(1, ccaManager.getCCAList().size());
        assertEquals("CCA basketball already exists.", ui.getLastMessage());
    }
}