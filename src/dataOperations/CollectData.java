package dataOperations;

import entities.Employee;
import entities.Project;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectData {
    private Map<String, Long> result;
    private InputParser parser;

    public CollectData(File file) {
        this.parser = new InputParser(file);
        this.result = new LinkedHashMap<>();
    }

    public List<String> getResults() {
        pairEmployees();

        return this.result.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(e -> String.format("%s, %d\n", e.getKey(), e.getValue())).collect(Collectors.toList());
    }

    private void pairEmployees() {
        Map<Integer, Employee> employeeMap = this.parser.getEmployees();

        for (Map.Entry<Integer, Employee> firstEmployee : employeeMap.entrySet()) {
            for (Map.Entry<Integer, Employee> secondEmployee : employeeMap.entrySet()) {

                if (firstEmployee.getKey().equals(secondEmployee.getKey())) {
                    continue;
                }

                checkProjects(firstEmployee, secondEmployee);
            }
        }
    }

    private void checkProjects(Map.Entry<Integer, Employee> firstEmployee, Map.Entry<Integer, Employee> secondEmployee) {
        List<Project> firstList = firstEmployee.getValue().getProjectList();
        List<Project> secondList = secondEmployee.getValue().getProjectList();

        for (Project projectFirstEmployee : firstList) {
            for (Project projectSecondEmployee : secondList) {

                if (projectFirstEmployee.getID() != projectSecondEmployee.getID()) {
                    continue;
                }

                fillMap(firstEmployee, secondEmployee, projectFirstEmployee, projectSecondEmployee);
            }
        }
    }

    private void fillMap(Map.Entry<Integer, Employee> firstEmployee, Map.Entry<Integer, Employee> secondEmployee, Project projectFirstEmployee, Project projectSecondEmployee) {

        LocalDate startDateFirstEmployee = projectFirstEmployee.getStartDate();
        LocalDate endDateFirstEmployee = projectFirstEmployee.getEndDate();

        LocalDate startDateSecondEmployee = projectSecondEmployee.getStartDate();
        LocalDate endDateSecondEmployee = projectSecondEmployee.getEndDate();

        if (startDateFirstEmployee.compareTo(startDateSecondEmployee) * startDateSecondEmployee.compareTo(endDateFirstEmployee) >= 0) {

            LocalDate startDateWork = startDateFirstEmployee.compareTo(startDateSecondEmployee) < 0 ? startDateSecondEmployee : startDateFirstEmployee;
            LocalDate endDateWork = endDateFirstEmployee.compareTo(endDateSecondEmployee) < 0 ? endDateFirstEmployee : endDateSecondEmployee;

            long until = startDateWork.until(endDateWork, ChronoUnit.DAYS) + 1;

            String format = String.format("%d, %d, %d", firstEmployee.getKey(), secondEmployee.getKey(), projectFirstEmployee.getID());

            this.result.put(format, until);
        }
    }
}