package ccamanager.command;

import ccamanager.manager.CcaManager;
import ccamanager.manager.ResidentManager;
import ccamanager.ui.Ui;

import ccamanager.exceptions.DuplicateResidentException;

/**
 * Command to add a resident and his CCA
 */
public class AddResidentCommand extends Command{
    private String residentName;
    private String matricNumber;

    public AddResidentCommand(String residentName,String matricNumber){
        this.residentName=residentName;
        this.matricNumber=matricNumber;
    }

    public void execute(CcaManager ccaManager, ResidentManager residentManager, Ui ui) {
        try {
            residentManager.addResident(residentName, matricNumber);
            ui.showMessage("Resident added: " + residentName + " " + matricNumber);
        } catch (DuplicateResidentException e) {
            ui.showError(e.getMessage());
        }
    }


}
