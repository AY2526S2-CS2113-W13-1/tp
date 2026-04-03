package ccamanager.command;


import ccamanager.exceptions.ResidentNotFoundException;
import ccamanager.manager.CcaManager;
import ccamanager.manager.EventManager;
import ccamanager.manager.ResidentManager;
import ccamanager.model.Resident;
import ccamanager.ui.Ui;

/**
 * Command to delete any exisiting CCA
 */
public class DeleteResidentCommand extends Command {

    private String matricNumber;

    public DeleteResidentCommand(String matricNumber){
        this.matricNumber=matricNumber;

    }

    @Override
    public void execute(CcaManager ccaManager, ResidentManager residentManager, EventManager eventManager, Ui ui) {
        try {
            Resident resident = residentManager.matchingResident(matricNumber);
            if(resident==null) {
                ui.showError("Resident with " + matricNumber + " does not exist");
                return;
            }
            String residentName = resident.getName();
            residentManager.deleteResident(matricNumber);
            ui.showMessage("Resident deleted: " + residentName);
        } catch (ResidentNotFoundException e) {
            ui.showMessage(e.getMessage());
        }
    }


}
