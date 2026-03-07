package ccamanager.parser;

import ccamanager.command.ExitCommand;
import ccamanager.command.Command;
import ccamanager.command.UnknownCommand;

/**
 * Parser — reads raw user input and returns the appropriate Command object.
 * <p>
 * ====================================================
 * FOR TEAMMATES ADDING NEW COMMANDS:
 * ====================================================
 * When you create a new Command class, add a case here to return it.
 * <p>
 * Steps:
 * 1. Add a new "case" entry below for your command word (e.g. "add-cca")
 * 2. Parse any arguments from the input (e.g. extract the name after "n/")
 * 3. Return a new instance of your Command, passing arguments to the constructor
 * <p>
 * Rules:
 * - Parser ONLY creates Command objects — it never executes them
 * - Parser NEVER touches CcaManager, ResidentManager, or Ui
 * - If input is invalid, return new UnknownCommand() — don't throw here
 * ====================================================
 */
public class Parser {

    /**
     * Parses the user's input string and returns the matching Command.
     *
     * @param input the raw input string from the user
     * @return the appropriate Command object to execute
     */
    public Command parse(String input) {
        if (input == null || input.isBlank()) {
            return new UnknownCommand();
        }

        String[] parts = input.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        if (commandWord.equals("bye")) {
            return new ExitCommand();

            // -------------------------------------------------------
            // CCA commands — Veer adds cases here
            // e.g. case "add-cca": return new AddCcaCommand(...)
            // -------------------------------------------------------

            // -------------------------------------------------------
            // Resident commands — Aarav / Yi Yang add cases here
            // e.g. case "add-resident": return new AddResidentCommand(...)
            // -------------------------------------------------------
        }
        return new UnknownCommand();
    }

    // ----------------------------------------------------------------
    // Helper methods for parsing arguments
    // ----------------------------------------------------------------

    /**
     * Extracts the value after a prefix like "n/" from an argument string.
     * Example: extractPrefix("n/Basketball", "n/") → "Basketball"
     *
     * @param arguments the argument string
     * @param prefix    the prefix to strip (e.g. "n/")
     * @return the value after the prefix, or empty string if not found
     */
    public static String extractPrefix(String arguments, String prefix) {
        if (arguments.contains(prefix)) {
            return arguments.substring(arguments.indexOf(prefix) + prefix.length()).trim();
        }
        return "";
    }
}
