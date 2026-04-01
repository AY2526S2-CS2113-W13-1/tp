package ccamanager;
import java.util.logging.LogManager;


/**
 * Entry point for CCA Ledger.
 * Instantiates CcaLedger and starts the run loop.
 */
public class Main {

    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        assert false : "dummy assertion set to fail";
        new CcaLedger().run();
    }
}
