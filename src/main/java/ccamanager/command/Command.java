package ccamanager.command;

import ccamanager.manager.CcaManager;
import ccamanager.manager.ResidentManager;
import ccamanager.ui.Ui;

/**
 * Command — abstract base class for all commands.
 * <p>
 * ====================================================
 * FOR TEAMMATES EXTENDING THIS CLASS:
 * ====================================================
 * 1. Create a new file in the command/ package, e.g. AddCcaCommand.java
 * 2. Extend Command:     public class AddCcaCommand extends Command
 * 3. Implement execute() — put your logic here, calling manager methods
 * 4. Store any parsed data (e.g. ccaName) as a constructor argument field
 * 5. Do NOT print directly — always use ui.showMessage() or ui methods
 * 6. Override isExit() ONLY in ExitCommand — leave it alone otherwise
 * <p>
 * EXAMPLE:
 * public class AddCcaCommand extends Command {
 * private String ccaName;
 * public AddCcaCommand(String ccaName) { this.ccaName = ccaName; }
 * <p>
 * Override public void execute(CcaManager ccaManager, ResidentManager residentManager, Ui ui) {
 * ccaManager.addCca(ccaName);
 * ui.showMessage("CCA added: " + ccaName);
 * }
 * }
 * ====================================================
 */
public abstract class Command {

    /**
     * Executes the command.
     * All real logic goes here — call manager methods, then call ui methods to print.
     *
     * @param ccaManager      manages the list of CCAs
     * @param residentManager manages the list of Residents
     * @param ui              used to display output — ONLY class that should print
     */
    public abstract void execute(CcaManager ccaManager, ResidentManager residentManager, Ui ui);

    /**
     * Returns true if this command should end the application loop.
     * Only ExitCommand should override this to return true.
     *
     * @return false by default
     */
    public boolean isExit() {
        return false;
    }
}
