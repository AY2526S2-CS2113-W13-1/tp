package ccamanager.command;

import ccamanager.manager.CcaManager;
import ccamanager.manager.ResidentManager;
import ccamanager.model.Cca;
import ccamanager.model.Resident;
import ccamanager.ui.Ui;

public class AddResidentToCcaCommand extends Command {

    private final String matriculationNo;
    private final String ccaName;
    private final int pointsScored;

    public AddResidentToCcaCommand(String matriculationNo, String ccaName, String pointsScored) {
        this.matriculationNo = matriculationNo;
        this.ccaName = ccaName;
        this.pointsScored = Integer.parseInt(pointsScored);
    }

    public void execute(CcaManager ccaManager, ResidentManager residentManager, Ui ui) {

        Cca cca = (Cca) ccaManager.getCCAList().stream().filter(x -> x.getName().equals(ccaName)).toArray()[0];
        Resident resident =
                (Resident) residentManager.getResidentList().stream().filter(x -> x.getMatricNumber()
                        .equals(matriculationNo)).toArray()[0];

        cca.addResidentToCca(resident);
        resident.addCcaToResident(cca, pointsScored);

        ui.showMessage("Resident " + resident + " was added to CCA :" + cca +" with " + pointsScored + " points.");

    }


}


