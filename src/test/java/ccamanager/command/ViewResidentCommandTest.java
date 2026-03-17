package ccamanager.command;

import ccamanager.manager.CcaManager;
import ccamanager.manager.ResidentManager;
import ccamanager.model.Resident;
import ccamanager.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewResidentCommandTest {

    private CcaManager ccaManager;
    private ResidentManager residentManager;
    private StubUi stubUi;

    @BeforeEach
    public void setUp() {
        ccaManager = new CcaManager();
        residentManager = new ResidentManager();
        stubUi = new StubUi();
    }

    @Test
    public void execute_emptyResidentList_showsEmptyList() {
        // Arrange
        ViewResidentCommand command = new ViewResidentCommand();

        // Act
        command.execute(ccaManager, residentManager, stubUi);

        // Assert
        assertTrue(stubUi.wasShowResidentListCalled, "The command should tell the UI to show the resident list.");
        assertEquals(0, stubUi.capturedList.size(), "The list passed to the UI should be empty.");
    }

    @Test
    public void execute_populatedResidentList_showsPopulatedList() {
        // Arrange: Add dummy residents to the manager
        // (Note: Adjust the "new Resident(...)" parameters to match your actual Resident constructor)
        residentManager.getResidentList().add(new Resident("John Doe", "A0123456X"));
        residentManager.getResidentList().add(new Resident("Jane Smith", "A0987654Y"));

        ViewResidentCommand command = new ViewResidentCommand();

        // Act
        command.execute(ccaManager, residentManager, stubUi);

        // Assert
        assertTrue(stubUi.wasShowResidentListCalled, "The command should tell the UI to show the resident list.");
        assertEquals(2, stubUi.capturedList.size(), "The list passed to the UI should contain exactly 2 residents.");
    }

    // --- Stub Class ---
    // This fake UI intercepts the call so we can check the data without actually printing to the console.
    private static class StubUi extends Ui {
        boolean wasShowResidentListCalled = false;
        ArrayList<Resident> capturedList = null;

        @Override
        public void showResidentList(ArrayList<Resident> residentList) {
            this.wasShowResidentListCalled = true;
            this.capturedList = residentList; // Save the list so the test can inspect it
        }
    }
}

