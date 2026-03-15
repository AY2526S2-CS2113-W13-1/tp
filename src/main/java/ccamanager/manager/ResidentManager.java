package ccamanager.manager;

import ccamanager.model.Resident;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles adding, deleting, viewing and giving CCA points to residents.
 */
public class ResidentManager {
    private static final Logger logger = Logger.getLogger(ResidentManager.class.getName());
    private static ArrayList<Resident> residents;

    public ResidentManager() {
        residents = new ArrayList<>();
    }

    /**
     * Create a new resident object
     * @param residentName name of the resident
     * @param matricNumber unique matric number for the resident
     */
    public static void addResident(String residentName, String matricNumber) {
        Resident resident  = new Resident(residentName,matricNumber);

        residents.add(resident);
        logger.log(Level.INFO, "Successfully deleted resident: {0}", residentName);
    }


    /**
     * Return the list of all the residents
     * @return List of residents
     */
    public ArrayList<Resident> getResidentList() {
        return this.residents;
    }
}
