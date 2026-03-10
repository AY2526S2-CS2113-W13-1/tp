package ccamanager.command;

import ccamanager.manager.CcaManager;
import ccamanager.manager.ResidentManager;
import ccamanager.ui.Ui;

/**
 * UnknownCommand — returned by Parser when input doesn't match any known command.
 * Displays an error message. Teammates do not need to touch this file.
 */
public class UnknownCommand extends Command {

    @Override
    public void execute(CcaManager ccaManager, ResidentManager residentManager, Ui ui) {
        ui.showError("Unknown command. Type 'help' for a list of commands.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
