package ccamanager.command;

import ccamanager.manager.CcaManager;
import ccamanager.manager.EventManager;
import ccamanager.manager.ResidentManager;
import ccamanager.ui.Ui;

/**
 * Base class for all commands.
 * <p>
 * Subclasses implement execute() with their specific logic.
 * View/stats commands should also override isReadOnly() to return true
 * so CcaLedger skips the save step after they run.
 */
public abstract class Command {

    /**
     * Executes the command.
     * All real logic goes here — call manager methods, then call ui methods to print.
     *
     * @param ccaManager      manages the list of CCAs
     * @param residentManager manages the list of Residents
     * @param eventManager    manages the list of Events
     * @param ui              used to display output — ONLY class that should print
     */
    public abstract void execute(CcaManager ccaManager, ResidentManager residentManager,
                                 EventManager eventManager, Ui ui);

    /**
     * Returns true if this command should end the application loop.
     * Only ExitCommand should override this to return true.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns true if this command does not mutate any data.
     * CcaLedger uses this to skip the save step after read-only commands,
     * avoiding unnecessary disk writes.
     * <p>
     * Override to return true in:
     *   ViewCcaCommand, ViewResidentCommand, ViewCcaExco, ViewCcaEvents,
     *   ViewMyEvents, ViewPointsCommand, CcaStatsCommand, ResidentStatsCommand,
     *   HelpCommand, UnknownCommand
     */
    public boolean isReadOnly() {
        return false;
    }
}
