package ccamanager.manager;

import ccamanager.model.Resident;

import ccamanager.exceptions.DuplicateResidentException;

import java.util.ArrayList;

/**
 * Handles adding, deleting, viewing and giving CCA points to residents.
 */
public class ResidentManager {
    private static ArrayList<Resident> residents;

    public ResidentManager() {
        residents = new ArrayList<>();
    }

    /**
     * Create a new resident object
     * @param residentName name of the resident
     * @param matricNumber unique matric number for the resident
     */
    public void addResident(String residentName, String matricNumber) throws DuplicateResidentException {
        boolean isDuplicate = residents.stream()
                .anyMatch(x -> x.getMatricNumber().equalsIgnoreCase(matricNumber));
        if (isDuplicate) {
            throw new DuplicateResidentException("Resident with matric number " + matricNumber + " already exists.");
        }
        residents.add(new Resident(residentName, matricNumber));
    }


    /**
     * Return the list of all the residents
     * @return List of residents
     */
    public ArrayList<Resident> getResidentList() {
        return this.residents;
    }
}
