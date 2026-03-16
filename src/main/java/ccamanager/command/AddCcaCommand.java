package ccamanager.command;

import ccamanager.manager.CcaManager;
import ccamanager.manager.ResidentManager;
import ccamanager.ui.Ui;

import ccamanager.exceptions.DuplicateCcaException;

/**
 * Command to create and add a new CCA to the CCA manager
 */
public class AddCcaCommand extends Command {
    private String ccaName;

    public AddCcaCommand(String ccaName) {
        assert ccaName != null : "CCA name should not be null";
        this.ccaName = ccaName;
    }

    @Override
    public void execute(CcaManager ccaManager, ResidentManager residentManager, Ui ui) {
        try {
            ccaManager.addCCA(ccaName);
            ui.showMessage("CCA added: " + ccaName);
        } catch (DuplicateCcaException e) {
            ui.showError(e.getMessage());
        }
    }
}
