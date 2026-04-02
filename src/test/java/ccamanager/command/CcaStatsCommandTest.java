package ccamanager.command;

import ccamanager.manager.CcaManager;
import ccamanager.manager.EventManager;
import ccamanager.manager.ResidentManager;
import ccamanager.model.Cca;
import ccamanager.parser.Parser;
import ccamanager.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static ccamanager.enumerations.CcaLevel.HIGH;
import static ccamanager.enumerations.CcaLevel.MEDIUM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CcaStatsCommandTest {
    private CcaManager ccaManager;
    private ResidentManager residentManager;
    private EventManager eventManager;
    private Ui ui = new Ui();
    private Parser parser =  new Parser();

    @BeforeEach
    void setUp() {
        ccaManager = new CcaManager();
        residentManager = new ResidentManager();
        eventManager = new EventManager();
    }

    @Test
    void avgPoints_success() {
        Command addCcaBasketball = parser.parse("add-cca Basketball HIGH");
        addCcaBasketball.execute(ccaManager, residentManager, eventManager, ui);
        Command addCcaFootball = parser.parse("add-cca Football MEDIUM");
        addCcaFootball.execute(ccaManager, residentManager, eventManager, ui);
        Command addResidentJohn = parser.parse("add-resident John 1234");
        addResidentJohn.execute(ccaManager, residentManager, eventManager, ui);
        Command addResidentJames = parser.parse("add-resident James 4321");
        addResidentJames.execute(ccaManager, residentManager, eventManager, ui);
        Command addJohnToBasketball = parser.parse("add-resident-to-cca 1234 Basketball 9");
        addJohnToBasketball.execute(ccaManager, residentManager, eventManager, ui);
        Command addJamesToBasketball = parser.parse("add-resident-to-cca 4321 Basketball 8");
        addJamesToBasketball.execute(ccaManager, residentManager, eventManager, ui);
        ArrayList<Cca> ccas = ccaManager.getCCAList();
        assert ccas.size() == 2;
        assert residentManager.getResidentList().size() == 2;
        HashMap<Cca, Double> expectedAvgPoints = new HashMap<>();
        expectedAvgPoints.put(new Cca("Basketball", HIGH), 8.5);
        expectedAvgPoints.put(new Cca("Football", MEDIUM), 0.0);
        assertEquals(expectedAvgPoints, CcaStatsCommand.avgPoints(ccas));
    }

    @Test
    void avgPoints_noCcas_failure() {
        ArrayList<Cca> ccas = ccaManager.getCCAList();
        assert ccas.isEmpty();
        boolean caughtException = false;
        try {
            HashMap<Cca, Double> avgPoints = CcaStatsCommand.avgPoints(ccas);
        } catch (IllegalArgumentException e) {
            caughtException = true;
        }
        assertTrue(caughtException);
    }
}
