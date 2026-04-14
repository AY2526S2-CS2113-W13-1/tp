# Yi Yang - Project Portfolio Page

## Project: CCAManager
CCAManager is a CLI-based application designed for Hall Leaders to manage Resident CCA records, track event participation, and analyze performance points.

---

## Summary of Contributions

### 1. Delete Resident Feature
* **Implementation:** Developed the `DeleteResidentCommand` for removing residents via matric number.
* **Validation:** Added error handling for non-existent residents and empty lists.
* **Data Consistency:** Ensured proper state update post-deletion.

### 2. View Points Feature
* **Implementation:** Created `ViewPointsCommand` to display residents' total CCA points with breakdowns.
* **Logic:** Calculated and displayed points accurately using CCA and resident records.
* **UI:** Structured output for easy readability.

### 3. View CCA Events Feature
* **Implementation:** Implemented `ViewCcaEventsCommand` to display events for a specified CCA.
* **Filtering:** Only relevant events are shown for the selected CCA.
* **Error Handling:** Handled empty or non-existing events.

### 4. View My Events Feature
* **Implementation:** Built `ViewMyEventsCommand` for residents to view their event participation.
* **Logic:** Retrieved and displayed events linked to each resident in a user-friendly format.

### 5. Update Points Feature
* **Implementation:** Developed `UpdateCcaPointCommand` for updating a resident's points in a specific CCA.
* **Validation:** Ensured non-negative points and checked existence of both resident and CCA.
* **Encapsulation:** Delegated point update logic to `Resident.updatePoint()` for consistency.

### 6. Sort Points Feature
* **Implementation:** Implemented `SortPointsCommand` to sort residents by total points in descending order.
* **Logic:** Used `Comparator` for sorting and displayed the sorted list for easier identification of top performers.

### 7. Testing & Quality Assurance
* **Unit Testing:** Wrote JUnit tests for `DeleteResidentCommand`, `ViewPointsCommand`, `ViewCcaEventsCommand`, and `ViewMyEventsCommand`.
* **Edge Cases:** Tested for invalid inputs and empty lists to ensure robustness.
* **Quality Assurance:** Verified that features work as expected and meet requirements.

---

## Contributions to the User Guide
* Wrote instructions for `view-points`, `view-cca-events`, `view-my-events`, `sort-points`, and `edit-points`.

## Contributions to the Developer Guide
* Documented implementation details for `DeleteResidentCommand`, `ViewPointsCommand`, `ViewCcaEventsCommand`, `UpdateCcaPointCommand`, `ViewMyEventsCommand`, and `SortPointsCommand`.
* Explained design considerations and command execution behavior.

---

## Contributions to the Team Project

### Feature Development
* Led the implementation of commands for resident management, point tracking, and event viewing.
* Focused on features that enhance data retrieval and presentation for users.

### Code Quality & Collaboration
* Maintained consistency with the project’s architecture and coding style.
* Collaborated with teammates on integrating features across different managers.

---

## Summary
* Implemented key features like resident deletion, point viewing, event viewing, and point sorting.
* Contributed unit tests for feature reliability.
* Enhanced user experience by improving how resident and event data are presented.