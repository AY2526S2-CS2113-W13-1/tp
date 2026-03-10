package ccamanager.model;

import java.util.ArrayList;

/**
 * Represents a hall resident.
 * Plain data model — fields only, no business logic.
 */
public class Resident {

    private String name;
    private String matricNumber;
    private ArrayList<Cca> CcaRegisteredIn = new ArrayList<Cca>();
    private ArrayList<Integer> points = new ArrayList<Integer>();

    /**
     * @param name the resident's full name, e.g. "John Tan"
     */
    public Resident(String name,String matricNumber) {
        this.name = name;
        this.matricNumber=matricNumber;
    }

    /**
     * @return the resident's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the resident's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void addCcaToResident(Cca cca) {
        CcaRegisteredIn.add(cca);
        points.add(0);
    }

    public void addCcaToResident(Cca cca, int pointsEarned) {
        CcaRegisteredIn.add(cca);
        points.add(pointsEarned);
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    @Override
    public String toString() {
        return name + " | " + matricNumber;
    }
}
