# Project Portfolio: Aarav Baori

## Project: CCALedger
CCALedger is a CLI-based application designed for Hall Leaders to manage Resident CCA records, track leadership roles (Exco), and analyze event participation efficiently.

---

## Summary of Contributions

### 1. Code Contributed
**Repo Analysis Link**: [Link to code](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=aarav&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=AaravBaori&tabRepo=AY2526S2-CS2113-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### 2. Enhancements Implemented

* **Core Skeleton Setup (v1.0):**
  * **What I did:** Helped set up the initial project structure and the main logic loop.
  * **Justification:** I wanted to provide a stable starting point for the team so everyone could start building their own features as early as possible without worrying about the basic command flow.

* **Exco Command Refinement:**
  * **Feature:** Implemented `AddExcoToEventCommand` and worked on fixing logic gaps in the Exco tracking system.
  * **Impact:** I focused on resolving issues where Exco data wasn't syncing correctly across different events, ensuring that the information hall leaders see is consistent and reliable.

* **Resident Filtering (`view-resident-in-cca`):**
  * **Feature:** Added the `view-resident-in-cca` command.
  * **Impact:** This was designed to help users navigate large lists easily by filtering residents based on their specific CCAs, making the tool more practical for daily hall management.

* **Refining Input Validation:**
  * **Enhancement:** Moved CCA input handling to **Java Enums**.
  * **Impact:** This was a small but important step in making the app more "crash-proof." By restricting inputs to specific categories, I helped reduce the number of bugs the team had to deal with during the later stages of development.

* **Bug Fixing & Stability:**
  * **Action:** Spent time identifying and patching edge-case parsing errors (like unusual symbol inputs) to make the CLI experience smoother for the end user.

### 3. Contributions to the User Guide (UG)
* Added clear instructions and syntax examples for the **Exco Management** and **Resident Viewing** features to ensure users can get started without confusion.
* Contributed to the workflow sections to help explain the "big picture" of how the app handles student data.

### 4. Contributions to the Developer Guide (DG)
* Drafted the **UML Architecture Diagram** and sequence diagrams for the features I worked on to help future developers understand our logic.
* Explained the reasoning behind using **Enums** for input handling so the team is aligned on our defensive programming approach.

### 5. Contributions to Team-Based Tasks
* **Collaborative Support:** Since I helped with the early setup of the project, I enjoyed helping teammates troubleshoot when they ran into issues integrating their features into the core loop.
* **Peer Feedback:** Actively participated in reviewing PRs and offering suggestions, focusing on keeping our code consistent and supporting the overall quality of the team's work.
* **Collective Testing:** Pitched in during the final integration phases to help find and fix small bugs, ensuring the app was stable and reliable for the final release.