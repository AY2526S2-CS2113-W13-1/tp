package ccamanager.model;

/**
 * Represents a hall resident.
 * Plain data model — fields only, no business logic.
 * Fields: name (String), ccaName (String), points (int).
 * Add new fields here if needed (e.g. room number); logic goes in ResidentManager.
 */
public class Resident {

    private String name;
    private String ccaName;  // CCA this resident belongs to
    private int points;      // participation points, defaults to 0

    /**
     * @param name the resident's full name, e.g. "John Tan"
     */
    public Resident(String name) {
        this.name = name;
        this.ccaName = "";
        this.points = 0;
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

    /**
     * @return the CCA name this resident is assigned to
     */
    public String getCcaName() {
        return ccaName;
    }

    /**
     * @param ccaName name of the CCA to assign this resident to
     */
    public void setCcaName(String ccaName) {
        this.ccaName = ccaName;
    }

    /**
     * @return participation points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points points to assign; call via ResidentManager, not directly from commands
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return formatted string: "name | CCA: ccaName | Points: points"
     */
    @Override
    public String toString() {
        return name + " | CCA: " + ccaName + " | Points: " + points;
    }
}
