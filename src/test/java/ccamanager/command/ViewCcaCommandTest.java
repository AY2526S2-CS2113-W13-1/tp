package ccamanager.command;

import ccamanager.manager.CcaManager;
import ccamanager.manager.ResidentManager;
import ccamanager.model.Cca;
import ccamanager.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewCcaCommandTest {

    private CcaManager ccaManager;
    private ResidentManager residentManager;
    private StubUi stubUi;

    @BeforeEach
    public void setUp() {
        ccaManager = new CcaManager();
        residentManager = new ResidentManager();
        stubUi = new StubUi(); // We use our fake UI here
    }

    @Test
    public void execute_emptyCcaList_showsEmptyList() {
        // Arrange: The ccaManager is already empty from setUp()
        ViewCcaCommand command = new ViewCcaCommand();

        // Act
        command.execute(ccaManager, residentManager, stubUi);

        // Assert
        assertTrue(stubUi.wasShowCcaListCalled, "The command should tell the UI to show the list.");
        assertEquals(0, stubUi.capturedList.size(), "The list passed to the UI should be empty.");
    }

    @Test
    public void execute_populatedCcaList_showsPopulatedList() {
        // Arrange: Add some fake CCAs to the manager
        // (Note: Adjust the "new Cca(...)" parameters to match your actual Cca constructor)
        ccaManager.getCCAList().add(new Cca("Basketball"));
        ccaManager.getCCAList().add(new Cca("Chess Club"));

        ViewCcaCommand command = new ViewCcaCommand();

        // Act
        command.execute(ccaManager, residentManager, stubUi);

        // Assert
        assertTrue(stubUi.wasShowCcaListCalled, "The command should tell the UI to show the list.");
        assertEquals(2, stubUi.capturedList.size(), "The list passed to the UI should contain exactly 2 CCAs.");
    }

    // --- Stub Class ---
    // This fake UI intercepts the call so we can check the data without actually printing to the console.
    private static class StubUi extends Ui {
        boolean wasShowCcaListCalled = false;
        ArrayList<Cca> capturedList = null;

        @Override
        public void showCcaList(ArrayList<Cca> ccaList) {
            this.wasShowCcaListCalled = true;
            this.capturedList = ccaList; // Save the list so the test can inspect it
        }
    }
}