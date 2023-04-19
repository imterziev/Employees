package entities;

import java.time.LocalDate;

public class Project {
    private int ID;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(int ID, LocalDate startDate, LocalDate endDate) {
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getID() {
        return ID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}