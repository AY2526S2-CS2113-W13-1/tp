package ccamanager.command;



import ccamanager.manager.CcaManager;
import ccamanager.manager.EventManager;
import ccamanager.model.Resident;
import ccamanager.manager.ResidentManager;
import ccamanager.ui.Ui;

import java.util.ArrayList;
import java.util.Comparator;

public class SortPointsCommand extends Command {

    @Override
    public void execute(CcaManager ccaManager, ResidentManager residentManager, EventManager eventManager, Ui ui) {
        ArrayList<Resident> residents = residentManager.getResidentList();

        if (residents.isEmpty()) {
            ui.showMessage("There are no residents currently. Please add residents using add-resident command");
            return;
        }

        residents.sort(Comparator.comparingInt(Resident::getTotalPoints).reversed());

        ui.showMessage("Residents sorted by total points in descending order.");
        ui.showCcaPoints(residents);
    }
}
