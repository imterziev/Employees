package entities;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int ID;
    private List<Project> projectList;

    public Employee(int ID) {
        this.ID = ID;
        this.projectList = new ArrayList<>();
    }

    public void add(Project project) {
        this.projectList.add(project);
    }

    public List<Project> getProjectList() {
        return List.copyOf(projectList);
    }
}